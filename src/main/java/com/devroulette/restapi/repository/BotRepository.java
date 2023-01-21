package com.devroulette.restapi.repository;

import com.devroulette.restapi.entity.Bot;
import org.springframework.data.repository.CrudRepository;

public interface BotRepository extends CrudRepository<Bot, Long> {
}
