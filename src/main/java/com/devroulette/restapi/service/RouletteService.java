package com.devroulette.restapi.service;

import com.devroulette.restapi.constant.RouletteWorkflowState;
import com.devroulette.restapi.dto.RouletteDto;
import com.devroulette.restapi.entity.Roll;
import com.devroulette.restapi.factory.RollFactory;
import com.devroulette.restapi.jsEngine.BotScriptProcessor;
import com.devroulette.restapi.repository.RollRepository;
import com.devroulette.restapi.service.query.RollQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouletteService {

    private final BetService betService;
    private final BotScriptProcessor botScriptProcessor;
    private final CacheManager cacheManager;
    private final RollFactory rollFactory;
    private final RollQueryService rollQueryService;
    private final RollRepository rollRepository;

    private String nextRollTimeStamp;
    private RouletteWorkflowState rouletteWorkflowState = RouletteWorkflowState.ROLLING;

    @Cacheable("getCurrentRouletteState")
    public RouletteDto getCurrentRouletteState() {
        List<Integer> rollHistory = this.rollQueryService.getRollHistory();
        Float lastRollTileCoverage = this.rollQueryService.getLastRollTileCoverage();

        return new RouletteDto(rollHistory, lastRollTileCoverage, this.nextRollTimeStamp);
    }

    @Scheduled(cron = "*/10 * * * * *")
    protected void startRollWorkflow() {
        if (this.rouletteWorkflowState == RouletteWorkflowState.ROLLING) {
            this.cacheManager.getCache("getCurrentRouletteState").clear();

            this.botScriptProcessor.processBots();

            Roll roll = this.rollFactory.createRandomRoll();
            this.rollRepository.save(roll);

            // Process bets
            this.betService.processUserBets(roll);
            this.betService.processBotBets(roll);

            this.nextRollTimeStamp = ZonedDateTime.now().plusSeconds(30).format(DateTimeFormatter.ISO_INSTANT);
        }
    }

    public void setState(RouletteWorkflowState state) {
        this.rouletteWorkflowState = state;
    }
}
