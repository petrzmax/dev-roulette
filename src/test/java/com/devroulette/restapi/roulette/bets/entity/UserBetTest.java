package com.devroulette.restapi.roulette.bets.entity;

import com.devroulette.restapi.common.constant.BetType;
import com.devroulette.restapi.common.constant.ErrorMessages;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserBetTest {
    private final static long BET_AMOUNT = 100;

    @Test
    void userBetCreationShouldThrowExceptionWhenUserIsNull() {
        // when & then
        assertThatThrownBy(() -> new UserBet(BetType.GREEN, BET_AMOUNT, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.FIELD_IS_NULL);
    }
}