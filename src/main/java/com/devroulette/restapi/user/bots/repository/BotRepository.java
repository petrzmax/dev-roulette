package com.devroulette.restapi.user.bots.repository;

import com.devroulette.restapi.user.bots.entity.Bot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BotRepository extends CrudRepository<Bot, Long> {
    List<Bot> findAllByEnabledIsTrue();
}
