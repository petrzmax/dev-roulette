package com.devroulette.restapi.user.bots.restController;

import com.devroulette.restapi.common.constant.Endpoints;
import com.devroulette.restapi.user.bots.dto.BotCreationDto;
import com.devroulette.restapi.user.bots.dto.BotDto;
import com.devroulette.restapi.user.bots.dto.BotScriptDto;
import com.devroulette.restapi.user.bots.dto.BotStatusDto;
import com.devroulette.restapi.user.bots.entity.Bot;
import com.devroulette.restapi.user.bots.mapper.BotMapper;
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
    private final BotMapper botMapper;

    @GetMapping
    public ResponseEntity getUserBots() {
        List<BotDto> bots = this.botService.getUserBots();
        return new ResponseEntity(bots, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBot(@RequestBody BotCreationDto botDto) {
        Bot bot = this.botService.createBot(botDto);
        return new ResponseEntity(this.botMapper.toBotDto(bot), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteBot(@PathVariable long id) {
        this.botService.deleteBot(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}/script")
    public ResponseEntity updateBotScript(@PathVariable long id, @RequestBody BotScriptDto botDto) {
        Bot bot = this.botService.updateBotScript(id, botDto.scriptBody());
        return new ResponseEntity(this.botMapper.toBotDto(bot), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/status")
    public ResponseEntity updateBotStatus(@PathVariable long id, @RequestBody BotStatusDto botDto) {
        Bot bot = this.botService.updateBotStatus(id, botDto.status());
        return new ResponseEntity(this.botMapper.toBotDto(bot), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity handleException(NoSuchElementException e) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
