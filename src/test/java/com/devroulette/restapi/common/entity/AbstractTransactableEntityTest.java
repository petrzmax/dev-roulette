package com.devroulette.restapi.common.entity;

import com.devroulette.restapi.common.constant.ErrorMessages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class AbstractTransactableEntityTest {

    private static final long START_BALANCE = 1000;
    private static final long OPERATION_AMOUNT = 500;

    @Test
    void transferWithPositiveAmountShouldIncreaseEntityBalance() {
        // given
        long expectedBalance = START_BALANCE + OPERATION_AMOUNT;
        TransactableEntity entity = new TransactableEntity(START_BALANCE);

        // when
        entity.transfer(OPERATION_AMOUNT);

        // then
        assertThat(entity.getBalance()).isEqualTo(expectedBalance);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1})
    void transferShouldThrowExceptionWhenAmountIsLowerOrEqualZero(long amount) {
        // given
        TransactableEntity entity = new TransactableEntity(START_BALANCE);

        // when & then
        assertThatThrownBy(() -> entity.transfer(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.NEGATIVE_AMOUNT);
    }

    @Test
    void payWithPositiveAmountShouldDecreaseEntityBalance() {
        // given
        long expectedBalance = START_BALANCE - OPERATION_AMOUNT;
        TransactableEntity entity = new TransactableEntity(START_BALANCE);

        // when
        entity.pay(OPERATION_AMOUNT);

        // then
        assertThat(entity.getBalance()).isEqualTo(expectedBalance);
    }

    @Test
    void payShouldThrowExceptionWhenBalanceIsLowerThanPayAmount() {
        // given
        TransactableEntity entity = new TransactableEntity(START_BALANCE);

        // when & then
        assertThatThrownBy(() -> entity.pay(START_BALANCE + 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.NOT_ENOUGH_BALANCE);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1})
    void payShouldThrowExceptionWhenAmountIsLowerOrEqualZero(long amount) {
        // given
        TransactableEntity entity = new TransactableEntity(START_BALANCE);

        // when & then
        assertThatThrownBy(() -> entity.pay(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.NEGATIVE_AMOUNT);
    }


    private class TransactableEntity extends AbstractTransactableEntity {
        public TransactableEntity(long balance) {
            super(balance);
        }
    }
}