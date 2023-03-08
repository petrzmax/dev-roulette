package com.devroulette.restapi.bots.service;

import com.devroulette.restapi.bots.dto.BotCreationDto;
import com.devroulette.restapi.bots.entity.Bot;
import com.devroulette.restapi.bots.factory.BotFactory;
import com.devroulette.restapi.bots.repository.BotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotService {
    private final BotFactory botFactory;
    private final BotRepository botRepository;

    public void createBot(BotCreationDto botDto) {
        Bot bot = this.botFactory.getBot(botDto);
        this.botRepository.save(bot);
    }
}
