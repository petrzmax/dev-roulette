package com.devroulette.restapi.roulette.bets.service;

import com.devroulette.restapi.common.constant.BetType;
import com.devroulette.restapi.roulette.bets.entity.BotBet;
import com.devroulette.restapi.roulette.bets.entity.UserBet;
import com.devroulette.restapi.roulette.bets.repository.BotBetRepository;
import com.devroulette.restapi.roulette.bets.repository.UserBetRepository;
import com.devroulette.restapi.roulette.dto.BetDto;
import com.devroulette.restapi.user.bots.entity.Bot;
import com.devroulette.restapi.user.bots.repository.BotRepository;
import com.devroulette.restapi.user.entity.User;
import com.devroulette.restapi.user.repository.UserRepository;
import com.devroulette.restapi.user.service.AuthenticatedUserService;
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
