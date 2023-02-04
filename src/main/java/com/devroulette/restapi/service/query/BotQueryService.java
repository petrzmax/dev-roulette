package com.devroulette.restapi.service.query;

import com.devroulette.restapi.dto.BotDto;
import com.devroulette.restapi.entity.Bot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BotQueryService extends JpaRepository<Bot, Long> {
    List<BotDto> getUserBots(long userId);
}
