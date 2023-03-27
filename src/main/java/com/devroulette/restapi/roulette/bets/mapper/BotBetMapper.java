package com.devroulette.restapi.roulette.bets.mapper;

import com.devroulette.restapi.roulette.bets.dto.BotBetDto;
import com.devroulette.restapi.roulette.bets.entity.BotBet;
import org.mapstruct.Mapper;

@Mapper
public interface BotBetMapper {
    BotBetDto toBotBetDto(BotBet bet);
}