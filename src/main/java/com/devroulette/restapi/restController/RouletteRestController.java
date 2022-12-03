package com.devroulette.restapi.restController;

import com.devroulette.restapi.dto.BetDto;
import com.devroulette.restapi.dto.RouletteDto;
import com.devroulette.restapi.service.BetService;
import com.devroulette.restapi.service.RouletteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roulette")
@RequiredArgsConstructor
public class RouletteRestController {

    private final RouletteService rouletteService;
    private final BetService betService;

    @GetMapping("/state")
    public ResponseEntity<RouletteDto> getRouletteState() {
        return new ResponseEntity<>(this.rouletteService.getCurrentRouletteState(), HttpStatus.OK);
    }

    @PostMapping("bet")
    public ResponseEntity bet(@RequestBody BetDto betDto) {
        // TODO return account balance after bet & bet id? To allow bet cancel?
        // TODO BetDto validation
        try {
            this.betService.bet(betDto);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
}
