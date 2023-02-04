package com.devroulette.restapi.dto;

public record BotDto(
        long id,
        String name,
        long balance,
        boolean enabled,
        String errorMessage) {
}
