package com.devroulette.restapi.roulette.dto;

public record RouletteErrorDto(
        String message,
        long timeStamp) {
}
