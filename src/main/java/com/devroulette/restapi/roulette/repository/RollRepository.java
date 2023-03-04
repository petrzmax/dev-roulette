package com.devroulette.restapi.roulette.repository;

import com.devroulette.restapi.roulette.entity.Roll;
import org.springframework.data.repository.CrudRepository;

public interface RollRepository extends CrudRepository<Roll, Long> {
}
