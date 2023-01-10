package com.devroulette.restapi.dto;

import com.devroulette.restapi.constant.BetType;

public record BetDto(
        long amount,
        BetType betType) {
}
