package com.devroulette.restapi.roulette.bets.repository;

import com.devroulette.restapi.roulette.bets.entity.BotBet;
import org.springframework.data.repository.CrudRepository;

public interface BotBetRepository extends CrudRepository<BotBet, Long> {
    Iterable<BotBet> findAllByRollIsNull();
}
