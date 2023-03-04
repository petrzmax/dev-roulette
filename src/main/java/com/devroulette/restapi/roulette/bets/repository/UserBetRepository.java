package com.devroulette.restapi.roulette.bets.repository;

import com.devroulette.restapi.roulette.bets.entity.UserBet;
import org.springframework.data.repository.CrudRepository;

public interface UserBetRepository extends CrudRepository<UserBet, Long> {
}
