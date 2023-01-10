package com.devroulette.restapi.dto;

import java.sql.Timestamp;

public record RollDto(
        long id,
        String seed,
        int result,
        Timestamp timestamp) {
}
