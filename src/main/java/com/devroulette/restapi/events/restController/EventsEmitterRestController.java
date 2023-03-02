package com.devroulette.restapi.events.restController;

import com.devroulette.restapi.constant.Endpoints;
import com.devroulette.restapi.events.service.EventsEmitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping(Endpoints.EVENTS)
@RequiredArgsConstructor
public class EventsEmitterRestController {

    private final EventsEmitterService emitterService;

    @RequestMapping(value = "/subscribe")
    public SseEmitter subscribe() {
        return this.emitterService.subscribe();
    }
}
