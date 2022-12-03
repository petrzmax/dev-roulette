package com.devroulette.restapi.restController;

import com.devroulette.restapi.dto.SessionDto;
import com.devroulette.restapi.service.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class SessionRestController {
    private final AuthenticatedUserService authenticatedUserService;

    @GetMapping()
    public ResponseEntity getSession() {
        long balance = this.authenticatedUserService.getUser().getBalance();
        // TODO Pewnie querydsl które zwróci od razu dto
        return new ResponseEntity(new SessionDto(balance), HttpStatus.OK);
    }
}
