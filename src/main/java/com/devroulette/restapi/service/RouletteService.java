package com.devroulette.restapi.service;

import com.devroulette.restapi.constant.RouletteWorkflowState;
import com.devroulette.restapi.dto.RouletteDto;
import com.devroulette.restapi.entity.Roll;
import com.devroulette.restapi.factory.RollFactory;
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

    private final RollQueryService rollQueryService;
    private final CacheManager cacheManager;
    private final RollFactory rollFactory;
    private final RollRepository rollRepository;
    private final BetService betService;

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
            Roll roll = this.rollFactory.createRandomRoll();
            this.rollRepository.save(roll);
            this.betService.processBets(roll);


            this.nextRollTimeStamp = ZonedDateTime.now().plusSeconds(30).format(DateTimeFormatter.ISO_INSTANT);
        }
    }

    public void setState(RouletteWorkflowState state) {
        this.rouletteWorkflowState = state;
    }
}
