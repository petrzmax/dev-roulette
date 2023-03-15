package com.devroulette.restapi.user.bots.dto;

public record BotDto(
        long id,
        String name,
        String scriptBody,
        long balance,
        boolean enabled,
        String errorMessage) {
}
