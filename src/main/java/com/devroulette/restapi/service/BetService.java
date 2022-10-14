package com.devroulette.restapi.service;

import com.devroulette.restapi.dto.BetDto;
import com.devroulette.restapi.entity.Bet;
import com.devroulette.restapi.entity.Roll;
import com.devroulette.restapi.entity.User;
import com.devroulette.restapi.repository.BetRepository;
import com.devroulette.restapi.repository.UserRepository;
import com.devroulette.restapi.service.query.BetQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BetService {

    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final BetQueryService betQueryService;

    public void bet(BetDto betDto) throws IllegalArgumentException {
        // TODO it's dummy implementation
        User currentUser = this.userRepository.findById(1L).get();
        // if current user balance is enough
        currentUser.pay(betDto.getAmount());
        Bet bet = new Bet(betDto.getBetType(), betDto.getAmount(), currentUser);
        this.betRepository.save(bet);
        this.userRepository.save(currentUser);
    }

    public void processBets(Roll roll) {
        List<Bet> betsToProcess = this.betQueryService.getAllNotProcessedBets();

        betsToProcess.forEach(bet -> {
            bet.setRoll(roll);
            bet.markAsProcessed();
            User user = bet.getUser();

            if (bet.isVictory()) {
                switch (bet.getBetType()) {
                    case RED -> user.transfer(bet.getAmount() * 2);
                    case BLACK -> user.transfer(bet.getAmount() * 2);
                    case GREEN -> user.transfer(bet.getAmount() * 36);
                    case EVEN -> user.transfer(bet.getAmount() * 2);
                    case ODD -> user.transfer(bet.getAmount() * 2);
                }
                this.userRepository.save(user);
            }
        });
        this.betRepository.saveAll(betsToProcess);
    }
}
