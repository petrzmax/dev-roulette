package com.devroulette.restapi.repository;

import com.devroulette.restapi.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
