package com.devroulette.restapi.admin.restController;

import com.devroulette.restapi.admin.dto.MessageDto;
import com.devroulette.restapi.common.constant.Endpoints;
import com.devroulette.restapi.events.service.EventsEmitterService;
import com.devroulette.restapi.roulette.controller.RouletteController;
import com.devroulette.restapi.roulette.enums.RouletteState;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.ADMIN)
@RequiredArgsConstructor
public class AdminPanelRestController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminPanelRestController.class);

    private final RouletteController rouletteController;
    private final EventsEmitterService emitterService;

    @PostMapping("start")
    public ResponseEntity start() {
        this.rouletteController.setState(RouletteState.ROLLING);
        LOG.info("Roulette started!");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("stop")
    public ResponseEntity stop() {
        this.rouletteController.setState(RouletteState.STOPPED);
        LOG.info("Roulette stopped!");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("dispatchMessage")
    public void dispatchMessage(@RequestBody MessageDto messageDto) {
        this.emitterService.dispatchEvent(messageDto.type(), messageDto.message());
    }
}
