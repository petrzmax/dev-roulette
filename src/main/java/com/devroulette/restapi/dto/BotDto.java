package com.devroulette.restapi.dto;

public record BotDto(
        long id,
        String name,
        String scriptBody,
        long balance,
        boolean enabled,
        String errorMessage) {
}
