package com.devroulette.restapi.roulette.controller;

import com.devroulette.restapi.events.dto.RollEventDto;
import com.devroulette.restapi.events.service.EventsEmitterService;
import com.devroulette.restapi.roulette.bets.service.BetProcessorService;
import com.devroulette.restapi.roulette.entity.Roll;
import com.devroulette.restapi.roulette.enums.RouletteState;
import com.devroulette.restapi.roulette.factory.RollFactory;
import com.devroulette.restapi.roulette.repository.RollRepository;
import com.devroulette.restapi.user.bots.jsEngine.BotScriptProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class RouletteController {
    private static String nextRollTimeStamp;
    private static RouletteState rouletteState = RouletteState.ROLLING;

    private final BotScriptProcessor botScriptProcessor;
    private final CacheManager cacheManager;
    private final RollFactory rollFactory;
    private final RollRepository rollRepository;
    private final BetProcessorService betProcessorService;
    private final EventsEmitterService emitterService;

    // TODO Refactor - different handling of next toll timestamp, maybe store in db etc.
    public static String getNextRollTimeStamp() {
        return nextRollTimeStamp;
    }

    public void setState(RouletteState state) {
        this.rouletteState = state;
    }

    @Scheduled(cron = "*/30 * * * * *")
    protected void startRollWorkflow() {
        if (this.rouletteState == RouletteState.ROLLING) {
            this.cacheManager.getCache("getCurrentRouletteState").clear();

            this.botScriptProcessor.processBots();

            Roll roll = this.rollFactory.createRandomRoll();
            this.rollRepository.save(roll);

            // Process bets
            this.betProcessorService.processUserBets(roll);
            this.betProcessorService.processBotBets(roll);

            this.nextRollTimeStamp = ZonedDateTime.now().plusSeconds(30).format(DateTimeFormatter.ISO_INSTANT);

            RollEventDto rollEventDto = new RollEventDto(roll.getResult(), roll.getTileCoverageFactor(), nextRollTimeStamp);
            // TODO event / dto factory?
            this.emitterService.dispatchEvent("roll", rollEventDto, roll.getId());
        }
    }
}
