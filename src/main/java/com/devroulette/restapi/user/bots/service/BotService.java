package com.devroulette.restapi.user.bots.service;

import com.devroulette.restapi.common.constant.ErrorMessages;
import com.devroulette.restapi.user.bots.dto.BotCreationDto;
import com.devroulette.restapi.user.bots.dto.BotDto;
import com.devroulette.restapi.user.bots.entity.Bot;
import com.devroulette.restapi.user.bots.enums.BotStatus;
import com.devroulette.restapi.user.bots.factory.BotFactory;
import com.devroulette.restapi.user.bots.repository.BotRepository;
import com.devroulette.restapi.user.bots.service.query.BotQueryService;
import com.devroulette.restapi.user.service.AuthenticatedUserService;
import com.mysema.commons.lang.Assert;
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

    public void patchBotScript(long id, String scriptBody) {
        Bot bot = this.getBotById(id);
        bot.setScriptBody(scriptBody);
        this.botRepository.save(bot);
    }

    public void patchBotStatus(long id, BotStatus status) {
        Assert.isTrue(status != BotStatus.FAILED, ErrorMessages.ILLEGAL_STATUS);

        Bot bot = this.getBotById(id);
        bot.setStatus(status);
        this.botRepository.save(bot);
    }

    private Bot getBotById(long id) {
        return this.botRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
