package com.devroulette.restapi.dto;

import com.devroulette.restapi.constant.BetType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BetDto {
    private BetType betType;
    private Long amount;
}
