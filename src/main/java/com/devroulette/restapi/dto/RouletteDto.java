package com.devroulette.restapi.dto;

import java.util.List;

public record RouletteDto(
        List<Integer> rollHistory,
        float tileCoverageFactor,
        String nextRollTimeStamp) {
}
