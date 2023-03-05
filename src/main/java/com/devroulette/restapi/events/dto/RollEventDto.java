package com.devroulette.restapi.events.dto;

public record RollEventDto(
        int result,
        float tileCoverageFactor,
        // TODO Time to roll?
        String nextRollTimeStamp) {
}
