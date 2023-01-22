package com.devroulette.restapi.service.query;

import com.devroulette.restapi.entity.UserBet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBetQueryService extends JpaRepository<UserBet, Long> {

    /**
     * @return list of all not yet processed user bets.
     */
    List<UserBet> getAllNotProcessedBets();
}
