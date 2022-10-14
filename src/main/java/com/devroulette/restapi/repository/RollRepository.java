package com.devroulette.restapi.repository;

import com.devroulette.restapi.entity.Roll;
import org.springframework.data.repository.CrudRepository;

public interface RollRepository extends CrudRepository<Roll, Long> {
    Roll findTopByOrderByIdDesc();
}
