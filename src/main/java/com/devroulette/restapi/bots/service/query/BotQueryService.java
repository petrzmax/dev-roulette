package com.devroulette.restapi.bots.service.query;

import com.devroulette.restapi.bots.dto.BotDto;
import com.devroulette.restapi.bots.entity.Bot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BotQueryService extends JpaRepository<Bot, Long> {
    List<BotDto> getUserBots(long userId);
}
