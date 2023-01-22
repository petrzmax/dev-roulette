package com.devroulette.restapi.repository;

import com.devroulette.restapi.entity.BotBet;
import org.springframework.data.repository.CrudRepository;

public interface BotBetRepository extends CrudRepository<BotBet, Long> {
    Iterable<BotBet> findAllByRollIsNull();
}
