package com.devroulette.restapi.user.bots.dto;

import com.devroulette.restapi.user.bots.enums.BotStatus;

public record BotStatusDto(
        BotStatus status) {
}
