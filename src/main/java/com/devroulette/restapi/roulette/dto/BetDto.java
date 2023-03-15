package com.devroulette.restapi.roulette.dto;

import com.devroulette.restapi.roulette.bets.enums.BetType;

public record BetDto(
        long amount,
        BetType betType) {
}
