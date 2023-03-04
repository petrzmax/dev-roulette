package com.devroulette.restapi.roulette.dto;

import com.devroulette.restapi.common.constant.BetType;

public record BetDto(
        long amount,
        BetType betType) {
}
