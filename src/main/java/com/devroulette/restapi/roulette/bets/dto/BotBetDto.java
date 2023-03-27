package com.devroulette.restapi.roulette.bets.dto;

import com.devroulette.restapi.roulette.bets.enums.BetType;

public record BotBetDto(
        long amount,
        BetType betType,
        boolean victory) {
}
