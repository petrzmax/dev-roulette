package com.devroulette.restapi.bots.repository;

import com.devroulette.restapi.bots.entity.Bot;
import org.springframework.data.repository.CrudRepository;

public interface BotRepository extends CrudRepository<Bot, Long> {
    Iterable<Bot> findAllByEnabledIsTrue();
}
