package com.devroulette.restapi.events.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

@Service
@RequiredArgsConstructor
public class EventsEmitterService {
    private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter subscribe() {
        // TODO choose smarter value
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            sseEmitter.send(event().name("INIT"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sseEmitter.onCompletion(() -> this.emitters.remove(sseEmitter));
        sseEmitter.onTimeout(() -> this.emitters.remove(sseEmitter));

        this.emitters.add(sseEmitter);
        return sseEmitter;
    }

    public void dispatchEvent(String data) {
        var test = event().data(data).id("TEST").name("test").build();

        this.emitters.stream().forEach(emitter -> {
            try {
                emitter.send(data);
            } catch (IOException e) {
                // If it's not present, remove it
                this.emitters.remove(emitter);
            }
        });
    }
}
