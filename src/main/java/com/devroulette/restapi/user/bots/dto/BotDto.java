package com.devroulette.restapi.user.bots.dto;

import com.devroulette.restapi.user.bots.enums.BotStatus;

public record BotDto(
        long id,
        String name,
        String scriptBody,
        long balance,
        BotStatus status,
        String errorMessage) {
}
