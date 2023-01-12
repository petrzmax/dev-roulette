package com.devroulette.restapi.entity;

import com.devroulette.restapi.constant.ErrorMessages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserTest {

    private static final String USERNAME = "username";
    private static final String ROLE = "role";

    private static final long START_BALANCE = 1000;
    private static final long OPERATION_AMOUNT = 500;

    @Test
    void transferWithPositiveAmountShouldIncreaseUserBalance() {
        // given
        long expectedBalance = START_BALANCE + OPERATION_AMOUNT;
        User user = new User(USERNAME, START_BALANCE, ROLE);

        // when
        user.transfer(OPERATION_AMOUNT);

        // then
        assertThat(user.getBalance()).isEqualTo(expectedBalance);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1, -10})
    void transferShouldThrowExceptionWhenAmountIsLowerOrEqualZero(long amount) {
        // given
        User user = new User(USERNAME, START_BALANCE, ROLE);

        // when & then
        assertThatThrownBy(() -> user.transfer(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.NEGATIVE_AMOUNT);
    }

    @Test
    void payWithPositiveAmountShouldDecreaseUserBalance() {
        // given
        long expectedBalance = START_BALANCE - OPERATION_AMOUNT;
        User user = new User(USERNAME, START_BALANCE, ROLE);

        // when
        user.pay(OPERATION_AMOUNT);

        // then
        assertThat(user.getBalance()).isEqualTo(expectedBalance);
    }

    @Test
    void payShouldThrowExceptionWhenBalanceIsLowerThanPayAmount() {
        // given
        User user = new User(USERNAME, START_BALANCE, ROLE);

        // when & then
        assertThatThrownBy(() -> user.pay(START_BALANCE + 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.NOT_ENOUGH_BALANCE);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1, -10})
    void payShouldThrowExceptionWhenAmountIsLowerOrEqualZero(long amount) {
        // given
        User user = new User(USERNAME, START_BALANCE, ROLE);

        // when & then
        assertThatThrownBy(() -> user.pay(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.NEGATIVE_AMOUNT);
    }
}