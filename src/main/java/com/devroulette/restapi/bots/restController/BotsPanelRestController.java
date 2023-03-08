package com.devroulette.restapi.bots.restController;

import com.devroulette.restapi.bots.dto.BotCreationDto;
import com.devroulette.restapi.bots.dto.BotDto;
import com.devroulette.restapi.bots.entity.Bot;
import com.devroulette.restapi.bots.repository.BotRepository;
import com.devroulette.restapi.bots.service.BotService;
import com.devroulette.restapi.bots.service.query.BotQueryService;
import com.devroulette.restapi.common.constant.Endpoints;
import com.devroulette.restapi.user.service.AuthenticatedUserService;
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
    private final BotService botService;

    @GetMapping
    public ResponseEntity getBots() {
        long userId = this.authenticatedUserService.getUserId();
        List<BotDto> bots = this.botQueryService.getUserBots(userId);

        return new ResponseEntity(bots, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBot(@RequestBody BotCreationDto botDto) {
        Bot bot = this.botService.createBot(botDto);

        // TODO use some model mapper
        BotDto dto = new BotDto(bot.getId(), bot.getName(), bot.getScriptBody(), bot.getBalance(),
                bot.isEnabled(), bot.getErrorMessage());
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteBot(@PathVariable long id) {

        Optional<Bot> bot = this.botRepository.findById(id);

        if (bot.isPresent()) {
            this.botRepository.delete(bot.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
