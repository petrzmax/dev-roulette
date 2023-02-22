package com.devroulette.restapi.restController;

import com.devroulette.restapi.constant.Endpoints;
import com.devroulette.restapi.dto.BotDto;
import com.devroulette.restapi.dto.UserDataDto;
import com.devroulette.restapi.entity.User;
import com.devroulette.restapi.service.AuthenticatedUserService;
import com.devroulette.restapi.service.query.BotQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Endpoints.USER)
@RequiredArgsConstructor
public class UserRestController {
    private final AuthenticatedUserService authenticatedUserService;
    private final BotQueryService botQueryService;

    @GetMapping()
    public ResponseEntity getUserData() {
        User user = this.authenticatedUserService.getUser();
        long balance = user.getBalance();
        List<BotDto> bots = this.botQueryService.getUserBots(user.getId());

        // TODO Probably querydsl which will return dto
        return new ResponseEntity(new UserDataDto(balance, bots), HttpStatus.OK);
    }
}
