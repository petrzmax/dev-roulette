package com.devroulette.restapi.user.bots.service.query;

import com.devroulette.restapi.user.bots.dto.BotDto;
import com.devroulette.restapi.user.bots.entity.Bot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BotQueryService extends JpaRepository<Bot, Long> {
    List<BotDto> getUserBots(long userId);
}
