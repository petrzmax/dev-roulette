package com.devroulette.restapi.controller;

import com.devroulette.restapi.constant.RouletteWorkflowState;
import com.devroulette.restapi.entity.Roll;
import com.devroulette.restapi.factory.RollFactory;
import com.devroulette.restapi.jsEngine.BotScriptProcessor;
import com.devroulette.restapi.repository.RollRepository;
import com.devroulette.restapi.service.BetProcessorService;
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
    private static RouletteWorkflowState rouletteWorkflowState = RouletteWorkflowState.ROLLING;

    private final BotScriptProcessor botScriptProcessor;
    private final CacheManager cacheManager;
    private final RollFactory rollFactory;
    private final RollRepository rollRepository;
    private final BetProcessorService betProcessorService;

    // TODO Refactor - different handling of next toll timestamp, maybe store in db etc.
    public static String getNextRollTimeStamp() {
        return nextRollTimeStamp;
    }

    public void setState(RouletteWorkflowState state) {
        this.rouletteWorkflowState = state;
    }

    @Scheduled(cron = "*/30 * * * * *")
    protected void startRollWorkflow() {
        if (this.rouletteWorkflowState == RouletteWorkflowState.ROLLING) {
            this.cacheManager.getCache("getCurrentRouletteState").clear();

            this.botScriptProcessor.processBots();

            Roll roll = this.rollFactory.createRandomRoll();
            this.rollRepository.save(roll);

            // Process bets
            this.betProcessorService.processUserBets(roll);
            this.betProcessorService.processBotBets(roll);

            this.nextRollTimeStamp = ZonedDateTime.now().plusSeconds(30).format(DateTimeFormatter.ISO_INSTANT);
        }
    }
}
