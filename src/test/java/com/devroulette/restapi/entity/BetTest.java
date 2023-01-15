package com.devroulette.restapi.entity;

import com.devroulette.restapi.constant.BetType;
import com.devroulette.restapi.constant.ErrorMessages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BetTest {

    private final static long BET_AMOUNT = 100;
    private final static User USER = new User();

    @Test
    void betCreationShouldThrowExceptionWhenBetTypeIsNull() {
        // when & then
        assertThatThrownBy(() -> new Bet(null, BET_AMOUNT, USER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.FIELD_IS_NULL);
    }

    @Test
    void betCreationShouldThrowExceptionWhenUserIsNull() {
        // when & then
        assertThatThrownBy(() -> new Bet(BetType.GREEN, BET_AMOUNT, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.FIELD_IS_NULL);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1})
    void betCreationShouldThrowExceptionWhenAmountIsLowerOrEqualZero(long amount) {
        // when & then
        assertThatThrownBy(() -> new Bet(BetType.GREEN, amount, USER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.NEGATIVE_AMOUNT);
    }

    @Test
    void isVictoryShouldReturnTrueWhenBetIsWinning() {
        // given
        Bet bet = new Bet(BetType.GREEN, BET_AMOUNT, USER);
        Roll roll = mock(Roll.class);
        when(roll.getColor()).thenReturn(BetType.GREEN);

        bet.setRoll(roll);

        // when & then
        assertThat(bet.isVictory()).isTrue();
    }

    @Test
    void isVictoryShouldThrowExceptionWhenBetHasNoRollAssigned() {
        // given
        Bet bet = new Bet();

        // when & then
        assertThatThrownBy(() -> bet.isVictory())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ErrorMessages.ROLL_NOT_ASSIGNED);
    }

    @ParameterizedTest
    @CsvSource({"GREEN,36", "EVEN,2", "ODD,2", "RED,2", "BLACK,2"})
    void getPrizeShouldReturnProperlyCalculatedPrize(BetType betType, int prizeMultiplier) {
        // given
        Bet bet = new Bet(betType, BET_AMOUNT, USER);
        long expectedPrize = BET_AMOUNT * prizeMultiplier;

        // when
        long result = bet.getPrize();

        // then
        assertThat(result).isEqualTo(expectedPrize);
    }
}