package com.devroulette.restapi.user.bots.mapper;

import com.devroulette.restapi.user.bots.dto.BotDto;
import com.devroulette.restapi.user.bots.entity.Bot;
import org.mapstruct.Mapper;

@Mapper
public interface BotMapper {
    BotDto toBotDto(Bot bot);
}
