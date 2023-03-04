package com.devroulette.restapi.user.repository;

import com.devroulette.restapi.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String email);
}
