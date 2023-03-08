package com.devroulette.restapi.bots.factory;

import com.devroulette.restapi.bots.dto.BotCreationDto;
import com.devroulette.restapi.bots.entity.Bot;
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
        return bot;
    }
}
