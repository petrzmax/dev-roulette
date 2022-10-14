package com.devroulette.restapi.restController;

import com.devroulette.restapi.constant.RouletteWorkflowState;
import com.devroulette.restapi.dto.BetDto;
import com.devroulette.restapi.dto.RouletteDto;
import com.devroulette.restapi.service.BetService;
import com.devroulette.restapi.service.RouletteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/roulette")
@RequiredArgsConstructor
public class RouletteRestController {

    private final RouletteService rouletteService;
    private final BetService betService;

    @GetMapping("/state")
    public ResponseEntity<RouletteDto> getRouletteState() {
        return new ResponseEntity<>(this.rouletteService.getCurrentRouletteState(), HttpStatus.OK);
    }

    @PostMapping("start")
    public ResponseEntity start() {
        this.rouletteService.setState(RouletteWorkflowState.ROLLING);
        System.out.printf("Roulette started!");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("stop")
    public ResponseEntity stop() {
        this.rouletteService.setState(RouletteWorkflowState.STOPPED);
        System.out.printf("Roulette stopped!");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("bet")
    public ResponseEntity bet(@RequestBody BetDto betDto) {
        // TODO return account balance after bet & bet id? To allow bet cancel?
        try {
            this.betService.bet(betDto);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
}
