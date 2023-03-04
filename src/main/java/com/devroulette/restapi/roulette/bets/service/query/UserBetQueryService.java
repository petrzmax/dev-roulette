package com.devroulette.restapi.roulette.bets.service.query;

import com.devroulette.restapi.roulette.bets.entity.UserBet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBetQueryService extends JpaRepository<UserBet, Long> {

    /**
     * @return list of all not yet processed user bets.
     */
    List<UserBet> getAllNotProcessedBets();
}
