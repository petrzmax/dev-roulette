package com.devroulette.restapi.utils;

import java.util.List;

public class RouletteUtils {

    public static final List<Integer> ROULETTE_NUMBER_SEQUENCE = List.of(
            0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24,
            16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26);

    public static String getNumberColor(Integer value) {
        String result;

        if (value == 0) {
            result = "GREEN";
        } else if ((value >= 1 && value <= 10) || (value >= 19 && value <= 28)) {
            result = isEven(value) ? "BLACK" : "RED";
        } else if ((value >= 11 && value <= 18) || (value >= 29 && value <= 36)) {
            result = isEven(value) ? "RED" : "BLACK";
        } else {
            throw new IllegalArgumentException("The number is out of roulette range!");
        }
        return result;
    }

    public static boolean isEven(Integer value) {
        return value % 2 == 0;
    }

}
