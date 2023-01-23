package com.devroulette.restapi.service;

import com.devroulette.restapi.constant.BetType;
import com.devroulette.restapi.dto.BetDto;
import com.devroulette.restapi.entity.Bot;
import com.devroulette.restapi.entity.BotBet;
import com.devroulette.restapi.entity.User;
import com.devroulette.restapi.entity.UserBet;
import com.devroulette.restapi.repository.BotBetRepository;
import com.devroulette.restapi.repository.BotRepository;
import com.devroulette.restapi.repository.UserBetRepository;
import com.devroulette.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BetService {
    private static final Logger LOG = LoggerFactory.getLogger(BetService.class);

    private final UserBetRepository userBetRepository;
    private final BotBetRepository botBetRepository;
    private final UserRepository userRepository;
    private final BotRepository botRepository;
    private final AuthenticatedUserService authorizedUserService;

    public void bet(BetDto betDto) {

        // TODO analyse if saving this user is safe in terms of many same requests
        // https://www.baeldung.com/get-user-in-spring-security
        User currentUser = this.authorizedUserService.getUser();

        currentUser.pay(betDto.amount());
        UserBet userBet = new UserBet(betDto.betType(), betDto.amount(), currentUser);
        this.userBetRepository.save(userBet);
        this.userRepository.save(currentUser);

        LOG.info(String.format("Bet created by User with ID: %1$d. Amount: %2$d, type: %3$s",
                currentUser.getId(), betDto.amount(), betDto.betType()));
    }

    public void bet(Bot bot, long amount, BetType betType) {
        bot.pay(amount);
        BotBet bet = new BotBet(betType, amount, bot);
        this.botBetRepository.save(bet);
        this.botRepository.save(bot);

        LOG.info(String.format("Bet created by Bot with ID: %1$d. Amount: %2$d, type: %3$s",
                bot.getId(), amount, betType));
    }
}
