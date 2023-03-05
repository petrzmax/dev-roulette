package com.devroulette.restapi.roulette.controller;

import com.devroulette.restapi.bots.jsEngine.BotScriptProcessor;
import com.devroulette.restapi.common.constant.RouletteWorkflowState;
import com.devroulette.restapi.roulette.bets.service.BetProcessorService;
import com.devroulette.restapi.roulette.entity.Roll;
import com.devroulette.restapi.roulette.factory.RollFactory;
import com.devroulette.restapi.roulette.repository.RollRepository;
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