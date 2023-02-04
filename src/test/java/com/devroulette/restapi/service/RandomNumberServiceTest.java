package com.devroulette.restapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class RandomNumberServiceTest {
    @Test
    void name() {
        RandomNumberService randomNumberService = new RandomNumberService(new RestTemplate());
        System.out.println(randomNumberService.getRandomInteger());
    }
}