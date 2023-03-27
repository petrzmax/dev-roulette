package com.devroulette.restapi.roulette.service;

import com.devroulette.restapi.roulette.controller.RouletteController;
import com.devroulette.restapi.roulette.dto.RouletteDto;
import com.devroulette.restapi.roulette.service.query.RollQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouletteService {
    public static final int ROLL_HISTORY_LENGTH = 10;

    private final RollQueryService rollQueryService;

    @Cacheable("getCurrentRouletteState")
    public RouletteDto getCurrentRouletteState() {
        List<Integer> rollHistory = this.rollQueryService.getRollHistory(ROLL_HISTORY_LENGTH);
        Collections.reverse(rollHistory);
        Float lastRollTileCoverage = this.rollQueryService.getLastRollTileCoverage();

        return new RouletteDto(rollHistory, lastRollTileCoverage, RouletteController.getNextRollTimeStamp());
    }
}
