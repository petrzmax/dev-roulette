package com.devroulette.restapi.factory;

import com.devroulette.restapi.constant.Role;
import com.devroulette.restapi.entity.User;
import com.devroulette.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final UserRepository userRepository;

    @Value("${roulette.initialbalance}")
    private long initialBalance;

    public User createNewUser(String email) {
        User user = new User(email, this.initialBalance, Role.USER);
        this.userRepository.save(user);
        return user;
    }
}
