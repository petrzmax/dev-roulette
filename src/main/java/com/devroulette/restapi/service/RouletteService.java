package com.devroulette.restapi.service;

import com.devroulette.restapi.controller.RouletteController;
import com.devroulette.restapi.dto.RouletteDto;
import com.devroulette.restapi.service.query.RollQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouletteService {

    private final RollQueryService rollQueryService;

    @Cacheable("getCurrentRouletteState")
    public RouletteDto getCurrentRouletteState() {
        List<Integer> rollHistory = this.rollQueryService.getRollHistory();
        Float lastRollTileCoverage = this.rollQueryService.getLastRollTileCoverage();

        return new RouletteDto(rollHistory, lastRollTileCoverage, RouletteController.getNextRollTimeStamp());
    }
}
