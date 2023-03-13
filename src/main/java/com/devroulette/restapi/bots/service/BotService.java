package com.devroulette.restapi.bots.service;

import com.devroulette.restapi.bots.dto.BotCreationDto;
import com.devroulette.restapi.bots.dto.BotDto;
import com.devroulette.restapi.bots.dto.BotPatchDto;
import com.devroulette.restapi.bots.entity.Bot;
import com.devroulette.restapi.bots.factory.BotFactory;
import com.devroulette.restapi.bots.repository.BotRepository;
import com.devroulette.restapi.bots.service.query.BotQueryService;
import com.devroulette.restapi.user.service.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BotService {
    private final AuthenticatedUserService authenticatedUserService;
    private final BotFactory botFactory;
    private final BotQueryService botQueryService;
    private final BotRepository botRepository;

    public List<BotDto> getUserBots() {
        long userId = this.authenticatedUserService.getUserId();
        List<BotDto> bots = this.botQueryService.getUserBots(userId);
        return bots;
    }

    public Bot createBot(BotCreationDto botDto) {
        Bot bot = this.botFactory.getBot(botDto);
        this.botRepository.save(bot);
        return bot;
    }

    public void deleteBot(long id) {
        Bot bot = this.getBotById(id);
        this.botRepository.delete(bot);
    }

    public void updateBotScript(long id, BotPatchDto botDto) {
        Bot bot = this.getBotById(id);
        bot.setScriptBody(botDto.scriptBody());
        this.botRepository.save(bot);
    }

    private Bot getBotById(long id) {
        return this.botRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
