package com.devroulette.restapi.bots.repository;

import com.devroulette.restapi.bots.entity.Bot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BotRepository extends CrudRepository<Bot, Long> {
    List<Bot> findAllByEnabledIsTrue();
}
