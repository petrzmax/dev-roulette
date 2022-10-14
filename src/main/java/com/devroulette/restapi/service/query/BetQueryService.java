package com.devroulette.restapi.service.query;

import com.devroulette.restapi.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetQueryService extends JpaRepository<Bet, Long> {

    /**
     * @return list of all not yet processed bets.
     */
    List<Bet> getAllNotProcessedBets();
}
