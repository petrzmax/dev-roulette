package com.devroulette.restapi;

import com.devroulette.restapi.entity.User;
import com.devroulette.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
//        this.loadUsers();
    }

    private void loadUsers() {
        this.userRepository.save(new User("test@test.pl", true, 5000L));
    }
}
