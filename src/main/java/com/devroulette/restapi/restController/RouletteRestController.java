package com.devroulette.restapi.restController;

import com.devroulette.restapi.constant.Endpoints;
import com.devroulette.restapi.dto.BetDto;
import com.devroulette.restapi.dto.RouletteDto;
import com.devroulette.restapi.dto.RouletteErrorDto;
import com.devroulette.restapi.service.BetService;
import com.devroulette.restapi.service.RouletteService;
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
