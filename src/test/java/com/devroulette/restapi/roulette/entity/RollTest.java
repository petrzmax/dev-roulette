package com.devroulette.restapi.roulette.entity;

import com.devroulette.restapi.common.constant.BetType;
import com.devroulette.restapi.common.constant.ErrorMessages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class RollTest {

    private static final float TILE_COVERAGE_FACTOR = 0.56f;
    private static final String SEED = "seed";

    @ParameterizedTest
    @ValueSource(ints = {-1, 37})
    void rollCreationShouldThrowExceptionWhenResultIsOutOfRouletteNumbersRange(int result) {
        // when & then
        assertThatThrownBy(() -> new Roll(SEED, result, TILE_COVERAGE_FACTOR))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.NUMBER_OUT_OF_ROULETTE_RANGE);

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 18, 36})
    void rollCreationShouldNotThrowAnyExceptionWhenResultIsInRouletteNumbersRange(int result) {
        // when & then
        assertThatCode(() -> new Roll(SEED, result, TILE_COVERAGE_FACTOR))
                .doesNotThrowAnyException();
    }

    @Test
    void getColorShouldReturnGreenWhenResultIsZero() {
        // given
        Roll roll = new Roll(SEED, 0, TILE_COVERAGE_FACTOR);

        // when & then
        assertThat(roll.getColor()).isEqualTo(BetType.GREEN);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35})
    void getColorShouldReturnBlackWhenResultIsOnBlackTile(int result) {
        // given
        Roll roll = new Roll(SEED, result, TILE_COVERAGE_FACTOR);

        // when & then
        assertThat(roll.getColor()).isEqualTo(BetType.BLACK);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36})
    void getColorShouldReturnRedWhenResultIsOnRedTile(int result) {
        // given
        Roll roll = new Roll(SEED, result, TILE_COVERAGE_FACTOR);

        // when & then
        assertThat(roll.getColor()).isEqualTo(BetType.RED);
    }

    @Test
    void getParityShouldReturnGreenWhenResultIsZero() {
        // given
        Roll roll = new Roll(SEED, 0, TILE_COVERAGE_FACTOR);

        // when & then
        assertThat(roll.getParity()).isEqualTo(BetType.GREEN);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36})
    void getParityShouldReturnEvenWhenResultIsEvenNumber(int result) {
        // given
        Roll roll = new Roll(SEED, result, TILE_COVERAGE_FACTOR);

        // when & then
        assertThat(roll.getParity()).isEqualTo(BetType.EVEN);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35})
    void getParityShouldReturnOddWhenResultIsOddNumber(int result) {
        // given
        Roll roll = new Roll(SEED, result, TILE_COVERAGE_FACTOR);

        // when & then
        assertThat(roll.getParity()).isEqualTo(BetType.ODD);
    }
}