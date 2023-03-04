package com.devroulette.restapi.roulette.restController;

import com.devroulette.restapi.common.constant.Endpoints;
import com.devroulette.restapi.roulette.bets.service.BetService;
import com.devroulette.restapi.roulette.dto.BetDto;
import com.devroulette.restapi.roulette.dto.RouletteDto;
import com.devroulette.restapi.roulette.dto.RouletteErrorDto;
import com.devroulette.restapi.roulette.service.RouletteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.ROULETTE)
@RequiredArgsConstructor
public class RouletteRestController {
    private final RouletteService rouletteService;
    private final BetService betService;

    @GetMapping()
    public ResponseEntity<RouletteDto> getRouletteState() {
        return new ResponseEntity<>(this.rouletteService.getCurrentRouletteState(), HttpStatus.OK);
    }

    @PostMapping("bet")
    public ResponseEntity bet(@RequestBody BetDto betDto) {
        // TODO return bet id? To allow bet cancel?
        this.betService.bet(betDto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @ExceptionHandler
    public ResponseEntity<RouletteErrorDto> handleException(IllegalArgumentException e) {

        RouletteErrorDto errorResponse = new RouletteErrorDto(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity(errorResponse, HttpStatus.PRECONDITION_FAILED);
    }
}
