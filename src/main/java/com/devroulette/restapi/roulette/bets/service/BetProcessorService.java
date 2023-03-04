package com.devroulette.restapi.roulette.bets.service;

import com.devroulette.restapi.bots.entity.Bot;
import com.devroulette.restapi.bots.repository.BotRepository;
import com.devroulette.restapi.roulette.bets.entity.BotBet;
import com.devroulette.restapi.roulette.bets.entity.UserBet;
import com.devroulette.restapi.roulette.bets.repository.BotBetRepository;
import com.devroulette.restapi.roulette.bets.repository.UserBetRepository;
import com.devroulette.restapi.roulette.bets.service.query.UserBetQueryService;
import com.devroulette.restapi.roulette.entity.Roll;
import com.devroulette.restapi.user.entity.User;
import com.devroulette.restapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BetProcessorService {
    private final BotBetRepository botBetRepository;
    private final BotRepository botRepository;
    private final UserBetQueryService userBetQueryService;
    private final UserBetRepository userBetRepository;
    private final UserRepository userRepository;

    // TODO Probably could be refactored - AbstractBettableEntity could have AbstractTransactableEntity etc.
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
