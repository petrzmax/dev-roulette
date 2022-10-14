package com.devroulette.restapi.repository;

import com.devroulette.restapi.entity.Bet;
import org.springframework.data.repository.CrudRepository;

public interface BetRepository extends CrudRepository<Bet, Long> {
}
