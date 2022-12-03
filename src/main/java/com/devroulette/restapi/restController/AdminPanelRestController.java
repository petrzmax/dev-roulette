package com.devroulette.restapi.restController;

import com.devroulette.restapi.constant.RouletteWorkflowState;
import com.devroulette.restapi.service.RouletteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class AdminPanelRestController {
    private final RouletteService rouletteService;

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
}
