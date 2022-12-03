package com.devroulette.restapi.restController;

import com.devroulette.restapi.dto.SessionDto;
import com.devroulette.restapi.entity.User;
import com.devroulette.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class SessionRestController {
    private final UserRepository userRepository;

    @GetMapping()
    public ResponseEntity getSession(Authentication authentication) {
        // TODO move to smth like AuthorizedUserService
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = this.userRepository.findByUsername(userDetails.getUsername()).get();
        // TODO Pewnie querydsl które zwróci od razu dto
        return new ResponseEntity(new SessionDto(currentUser.getBalance()), HttpStatus.OK);
    }
}
