package com.devroulette.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class RollDto {
    private Long id;
    private String seed;
    private Integer result;
    private Timestamp timestamp;
}
