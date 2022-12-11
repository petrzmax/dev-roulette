package com.devroulette.restapi.restController;

import com.devroulette.restapi.constant.Endpoints;
import com.devroulette.restapi.constant.RouletteWorkflowState;
import com.devroulette.restapi.service.RouletteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.ADMIN)
@RequiredArgsConstructor
public class AdminPanelRestController {
    private final RouletteService rouletteService;

    @PostMapping("start")
    public ResponseEntity start() {
        this.rouletteService.setState(RouletteWorkflowState.ROLLING);
        System.out.println("Roulette started!");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("stop")
    public ResponseEntity stop() {
        this.rouletteService.setState(RouletteWorkflowState.STOPPED);
        System.out.println("Roulette stopped!");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
