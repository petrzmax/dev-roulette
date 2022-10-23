package com.devroulette.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionDto {
    private String token;
    private Long balance;
}
