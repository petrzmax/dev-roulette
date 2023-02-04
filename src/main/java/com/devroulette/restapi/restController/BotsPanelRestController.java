package com.devroulette.restapi.restController;

import com.devroulette.restapi.constant.Endpoints;
import com.devroulette.restapi.dto.BotDto;
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
@RequestMapping(Endpoints.BOTS)
@RequiredArgsConstructor
public class BotsPanelRestController {
    private final AuthenticatedUserService authenticatedUserService;
    private final BotQueryService botQueryService;

    @GetMapping
    public ResponseEntity getBots() {
        long userId = this.authenticatedUserService.getUserId();
        List<BotDto> bots = this.botQueryService.getUserBots(userId);

        return new ResponseEntity(bots, HttpStatus.OK);
    }
}
