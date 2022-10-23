package com.devroulette.restapi.restController;

import com.devroulette.restapi.dto.SessionDto;
import com.devroulette.restapi.entity.User;
import com.devroulette.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class SessionRestController {
    private final UserRepository userRepository;

    @GetMapping()
    public ResponseEntity getSession() {
        // TODO it's dummy implementation
        User currentUser = this.userRepository.findById(1L).get();
        return new ResponseEntity(new SessionDto("JWT", currentUser.getBalance()), HttpStatus.OK);
    }
}
