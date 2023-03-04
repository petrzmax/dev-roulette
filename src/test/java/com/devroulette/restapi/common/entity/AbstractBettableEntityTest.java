package com.devroulette.restapi.common.entity;

import com.devroulette.restapi.common.constant.BetType;
import com.devroulette.restapi.common.constant.ErrorMessages;
import com.devroulette.restapi.roulette.entity.Roll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AbstractBettableEntityTest {

    private final static long BET_AMOUNT = 100;

    @Test
    void bettableEntityCreationShouldThrowExceptionWhenBetTypeIsNull() {
        // when & then
        assertThatThrownBy(() -> new BettableEntity(null, BET_AMOUNT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.FIELD_IS_NULL);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1})
    void bettableEntityCreationShouldThrowExceptionWhenAmountIsLowerOrEqualZero(long amount) {
        // when & then
        assertThatThrownBy(() -> new BettableEntity(BetType.GREEN, amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.NEGATIVE_AMOUNT);
    }

    @Test
    void isVictoryShouldReturnTrueWhenBetIsWinning() {
        // given
        BettableEntity bet = new BettableEntity(BetType.GREEN, BET_AMOUNT);
        Roll roll = mock(Roll.class);
        when(roll.getColor()).thenReturn(BetType.GREEN);

        bet.setRoll(roll);

        // when & then
        assertThat(bet.isVictory()).isTrue();
    }

    @Test
    void isVictoryShouldThrowExceptionWhenBetHasNoRollAssigned() {
        // given
        BettableEntity bet = new BettableEntity();

        // when & then
        assertThatThrownBy(() -> bet.isVictory())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ErrorMessages.ROLL_NOT_ASSIGNED);
    }

    @ParameterizedTest
    @CsvSource({"GREEN,36", "EVEN,2", "ODD,2", "RED,2", "BLACK,2"})
    void getPrizeShouldReturnProperlyCalculatedPrize(BetType betType, int prizeMultiplier) {
        // given
        BettableEntity bet = new BettableEntity(betType, BET_AMOUNT);
        long expectedPrize = BET_AMOUNT * prizeMultiplier;

        // when
        long result = bet.getPrize();

        // then
        assertThat(result).isEqualTo(expectedPrize);
    }

    private class BettableEntity extends AbstractBettableEntity {
        public BettableEntity() {
        }

        public BettableEntity(BetType betType, long amount) {
            super(betType, amount);
        }
    }
}