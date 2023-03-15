package com.devroulette.restapi.user.factory;

import com.devroulette.restapi.user.constant.Role;
import com.devroulette.restapi.user.entity.User;
import com.devroulette.restapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final UserRepository userRepository;

    @Value("${roulette.initialbalance}")
    private long initialBalance;

    public User getUser(String email) {
        User user = new User(email, this.initialBalance, Role.USER);
        // TODO it shouldn't be saved in factory
        this.userRepository.save(user);
        return user;
    }
}
