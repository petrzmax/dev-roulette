package com.devroulette.restapi.restController;

import com.devroulette.restapi.constant.Endpoints;
import com.devroulette.restapi.dto.UserDataDto;
import com.devroulette.restapi.service.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.USER)
@RequiredArgsConstructor
public class UserRestController {
    private final AuthenticatedUserService authenticatedUserService;

    @GetMapping()
    public ResponseEntity getBalance() {
        long balance = this.authenticatedUserService.getUser().getBalance();
        // TODO Probably querydsl which will return dto
        return new ResponseEntity(new UserDataDto(balance), HttpStatus.OK);
    }
}
