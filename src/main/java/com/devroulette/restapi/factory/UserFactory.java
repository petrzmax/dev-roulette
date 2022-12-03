package com.devroulette.restapi.factory;

import com.devroulette.restapi.entity.User;
import com.devroulette.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {

    // TODO move to properties
    private final static long INITIAL_BALANCE_AMOUNT = 5000;
    private final static boolean INITIAL_PREMIUM_STATE = false;

    private final UserRepository userRepository;

    public User createNewUser(String email) {
        User user = new User(email, INITIAL_PREMIUM_STATE, INITIAL_BALANCE_AMOUNT);
        this.userRepository.save(user);
        return user;
    }
}
