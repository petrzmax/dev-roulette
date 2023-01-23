package com.devroulette.restapi.service;

import com.devroulette.restapi.entity.*;
import com.devroulette.restapi.repository.BotBetRepository;
import com.devroulette.restapi.repository.BotRepository;
import com.devroulette.restapi.repository.UserBetRepository;
import com.devroulette.restapi.repository.UserRepository;
import com.devroulette.restapi.service.query.UserBetQueryService;
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
