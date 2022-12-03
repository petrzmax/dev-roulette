package com.devroulette.restapi.repository;

import com.devroulette.restapi.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String email);
}
