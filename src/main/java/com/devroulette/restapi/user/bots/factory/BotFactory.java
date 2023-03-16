package com.devroulette.restapi.user.bots.factory;

import com.devroulette.restapi.user.bots.dto.BotCreationDto;
import com.devroulette.restapi.user.bots.entity.Bot;
import com.devroulette.restapi.user.bots.enums.BotStatus;
import com.devroulette.restapi.user.service.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BotFactory {
    private final AuthenticatedUserService userService;

    @Value("${roulette.initialbotbalance}")
    private long initialBalance;

    public Bot getBot(BotCreationDto botDto) {
        Bot bot = new Bot(this.initialBalance, this.userService.getUser(), botDto.name());
        bot.setStatus(BotStatus.READY);
        return bot;
    }
}
