package com.devroulette.restapi.dto;

import java.util.List;

public record UserDataDto(
        long balance,
        List<BotDto> bots) {
}
