package com.devroulette.restapi.restController;

import com.devroulette.restapi.constant.Endpoints;
import com.devroulette.restapi.dto.BotDto;
import com.devroulette.restapi.entity.Bot;
import com.devroulette.restapi.repository.BotRepository;
import com.devroulette.restapi.service.AuthenticatedUserService;
import com.devroulette.restapi.service.query.BotQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Endpoints.BOTS)
@RequiredArgsConstructor
public class BotsPanelRestController {
    private final AuthenticatedUserService authenticatedUserService;
    private final BotQueryService botQueryService;
    private final BotRepository botRepository;

    @GetMapping
    public ResponseEntity getBots() {
        long userId = this.authenticatedUserService.getUserId();
        List<BotDto> bots = this.botQueryService.getUserBots(userId);

        return new ResponseEntity(bots, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity removeBot(@PathVariable long id) {

        Optional<Bot> bot = this.botRepository.findById(id);

        if (bot.isPresent()) {
            // TODO handle cascade settings (currently throws exception)
            this.botRepository.delete(bot.get());
            return new ResponseEntity(id, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
