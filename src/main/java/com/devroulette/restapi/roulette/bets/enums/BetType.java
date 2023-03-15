package com.devroulette.restapi.roulette.bets.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BetType {
    RED(2),
    BLACK(2),
    GREEN(36),
    EVEN(2),
    ODD(2),
    CUSTOM(36);

    private final int prizeMultiplier;

    public long getPrize(long amount) {
        return this.prizeMultiplier * amount;
    }
}
