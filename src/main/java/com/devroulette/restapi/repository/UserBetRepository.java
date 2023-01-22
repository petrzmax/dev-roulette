package com.devroulette.restapi.repository;

import com.devroulette.restapi.entity.UserBet;
import org.springframework.data.repository.CrudRepository;

public interface UserBetRepository extends CrudRepository<UserBet, Long> {
}
