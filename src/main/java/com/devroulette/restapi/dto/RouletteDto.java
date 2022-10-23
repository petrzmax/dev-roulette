package com.devroulette.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RouletteDto {
    private List<Integer> rollHistory;
    private Float tileCoverageFactor;
    private String nextRollTimeStamp;
}
