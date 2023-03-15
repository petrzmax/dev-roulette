package com.devroulette.restapi.roulette.bets.service;

import com.devroulette.restapi.roulette.bets.entity.BotBet;
import com.devroulette.restapi.roulette.bets.entity.UserBet;
import com.devroulette.restapi.roulette.bets.repository.BotBetRepository;
import com.devroulette.restapi.roulette.bets.repository.UserBetRepository;
import com.devroulette.restapi.roulette.bets.service.query.UserBetQueryService;
import com.devroulette.restapi.roulette.entity.Roll;
import com.devroulette.restapi.user.bots.entity.Bot;
import com.devroulette.restapi.user.bots.repository.BotRepository;
import com.devroulette.restapi.user.entity.User;
import com.devroulette.restapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    // TODO refactor, so Roll has a Bet list - it should be be easier to process. Roll will have processed flag
    public void processUserBets(Roll roll) {
        List<UserBet> betsToProcess = this.userBetQueryService.getAllNotProcessedBets();
        betsToProcess.forEach(bet -> bet.setRoll(roll));

        List<User> updatedUsers = betsToProcess.stream()
                .map(bet -> {
                    bet.setRoll(roll);
                    return bet;
                })
                .filter(UserBet::isVictory)
                .map(bet -> {
                    User user = bet.getUser();
                    user.transfer(bet.getPrize());
                    return user;
                }).toList();

        this.userRepository.saveAll(updatedUsers);
        this.userBetRepository.saveAll(betsToProcess);
    }

    public void processBotBets(Roll roll) {
        List<BotBet> betsToProcess = this.botBetRepository.findAllByRollIsNull();
        betsToProcess.forEach(bet -> bet.setRoll(roll));

        List<Bot> updatedBots = betsToProcess.stream()
                .map(bet -> {
                    bet.setRoll(roll);
                    return bet;
                })
                .filter(BotBet::isVictory)
                .map(bet -> {
                    Bot bot = bet.getBot();
                    bot.transfer(bet.getPrize());
                    return bot;
                }).toList();


        this.botRepository.saveAll(updatedBots);
        this.botBetRepository.saveAll(betsToProcess);
    }
}
