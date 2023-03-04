package com.devroulette.restapi.user.dto;

import com.devroulette.restapi.bots.dto.BotDto;

import java.util.List;

public record UserDataDto(
        long balance,
        List<BotDto> bots) {
}
