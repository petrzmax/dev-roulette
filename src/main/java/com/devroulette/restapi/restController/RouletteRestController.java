package com.devroulette.restapi.restController;

import com.devroulette.restapi.dto.RouletteDto;
import com.devroulette.restapi.service.RouletteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api")
@RequiredArgsConstructor
public class RouletteRestController {

    private final RouletteService rouletteService;

    @GetMapping("/roulette")
    public ResponseEntity<RouletteDto> getRouletteState() {
        return new ResponseEntity<>(this.rouletteService.getCurrentRouletteState(), HttpStatus.OK);
    }
}
