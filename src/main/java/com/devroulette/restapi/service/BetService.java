package com.devroulette.restapi.service;

import com.devroulette.restapi.constant.BetType;
import com.devroulette.restapi.dto.BetDto;
import com.devroulette.restapi.entity.*;
import com.devroulette.restapi.repository.BotBetRepository;
import com.devroulette.restapi.repository.BotRepository;
import com.devroulette.restapi.repository.UserBetRepository;
import com.devroulette.restapi.repository.UserRepository;
import com.devroulette.restapi.service.query.UserBetQueryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BetService {
    private static final Logger LOG = LoggerFactory.getLogger(BetService.class);

    private final UserBetRepository userBetRepository;
    private final BotBetRepository botBetRepository;
    private final UserRepository userRepository;
    private final BotRepository botRepository;
    private final UserBetQueryService userBetQueryService;
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

    // TODO move to BetProcessor class
    public void processUserBets(Roll roll) {
        List<UserBet> betsToProcess = this.userBetQueryService.getAllNotProcessedBets();
        List<User> updatedUsers = new ArrayList<>();

        betsToProcess.forEach(bet -> {
            bet.setRoll(roll);

            if (bet.isVictory()) {
                User user = bet.getUser();
                user.transfer(bet.getPrize());
                updatedUsers.add(user);
            }
        });

        this.userRepository.saveAll(updatedUsers);
        this.userBetRepository.saveAll(betsToProcess);
    }

    // TODO move to BetProcessor class
    public void processBotBets(Roll roll) {
        Iterable<BotBet> betsToProcess = this.botBetRepository.findAllByRollIsNull();
        List<Bot> updatedBots = new ArrayList<>();

        betsToProcess.forEach(bet -> {
            bet.setRoll(roll);

            if (bet.isVictory()) {
                Bot bot = bet.getBot();
                bot.transfer(bet.getPrize());
                updatedBots.add(bot);
            }
        });

        this.botRepository.saveAll(updatedBots);
        this.botBetRepository.saveAll(betsToProcess);
    }
}
