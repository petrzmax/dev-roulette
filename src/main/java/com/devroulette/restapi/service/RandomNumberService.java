package com.devroulette.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@RequiredArgsConstructor
public class RandomNumberService {
    // Configure your client to supply your email address in the User-Agent field of the request.
    // That way, I can drop you a line if your client is causing trouble.
    // https://www.random.org/clients/http/
    // https://www.random.org/clients/http/#quota

    private static final String URL = "https://www.random.org/integers/";
    private static final String EMAIL = "xxx";

    private final RestTemplate restTemplate;

    public int getRandomInteger() {
        return Integer.parseInt(this.fetchRandomNumberApi());
    }

    private String fetchRandomNumberApi() {
        HttpEntity request = new HttpEntity(this.getHeader());
        String url = this.prepareUrl(1, 0, 36, 1, 10);
        ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, request, String.class, 1, 10, 12);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().trim();
        } else {
            throw new RuntimeException(response.getBody());
        }
    }

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        headers.set(HttpHeaders.USER_AGENT, EMAIL);
        return headers;
    }

    private String prepareUrl(int numberQuantity, int min, int max, int columns, int base) {
        return UriComponentsBuilder.fromUriString(URL)
                .queryParam("num", numberQuantity)
                .queryParam("min", min)
                .queryParam("max", max)
                .queryParam("col", columns)
                .queryParam("base", base)
                .queryParam("format", "plain")
                .queryParam("rnd", "new")
                .toUriString();
    }

}
