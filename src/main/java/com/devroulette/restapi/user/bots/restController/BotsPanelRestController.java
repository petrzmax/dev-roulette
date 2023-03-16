package com.devroulette.restapi.user.bots.restController;

import com.devroulette.restapi.common.constant.Endpoints;
import com.devroulette.restapi.user.bots.dto.BotCreationDto;
import com.devroulette.restapi.user.bots.dto.BotDto;
import com.devroulette.restapi.user.bots.dto.BotPatchDto;
import com.devroulette.restapi.user.bots.entity.Bot;
import com.devroulette.restapi.user.bots.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(Endpoints.BOTS)
@RequiredArgsConstructor
public class BotsPanelRestController {
    private final BotService botService;

    @GetMapping
    public ResponseEntity getUserBots() {
        List<BotDto> bots = this.botService.getUserBots();
        return new ResponseEntity(bots, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBot(@RequestBody BotCreationDto botDto) {
        Bot bot = this.botService.createBot(botDto);

        // TODO use some model mapper. UPDATE: ModelMapper does not handle records
        BotDto dto = new BotDto(bot.getId(), bot.getName(), bot.getScriptBody(), bot.getBalance(),
                bot.getStatus(), bot.getErrorMessage());
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteBot(@PathVariable long id) {
        this.botService.deleteBot(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity patchBotScript(@PathVariable long id, @RequestBody BotPatchDto botDto) {
        this.botService.updateBotScript(id, botDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    public ResponseEntity handleException(NoSuchElementException e) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
