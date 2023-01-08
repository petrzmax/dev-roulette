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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BetService {
    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final BetQueryService betQueryService;
    private final AuthenticatedUserService authorizedUserService;

    public void bet(BetDto betDto) throws IllegalArgumentException {

        // TODO analyse if saving this user is safe in terms of many same requests
        // https://www.baeldung.com/get-user-in-spring-security
        long userId = this.authorizedUserService.getUser().getId();
        User currentUser = this.userRepository.findById(userId).get();


        // if current user balance is enough
        currentUser.pay(betDto.getAmount());
        Bet bet = new Bet(betDto.getBetType(), betDto.getAmount(), currentUser);
        this.betRepository.save(bet);
        this.userRepository.save(currentUser);
        System.out.println("Bet created. Amount: " + betDto.getAmount() + " type: " + betDto.getBetType());
    }

    // TODO probably move to some BetProcessor class
    public void processBets(Roll roll) {
        List<Bet> betsToProcess = this.betQueryService.getAllNotProcessedBets();
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
        this.betRepository.saveAll(betsToProcess);
    }
}
