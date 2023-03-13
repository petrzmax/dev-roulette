package com.devroulette.restapi.roulette.bets.repository;

import com.devroulette.restapi.roulette.bets.entity.BotBet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BotBetRepository extends CrudRepository<BotBet, Long> {
    List<BotBet> findAllByRollIsNull();
}
