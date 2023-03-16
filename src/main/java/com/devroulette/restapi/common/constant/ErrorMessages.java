package com.devroulette.restapi.common.constant;

public class ErrorMessages {
    public static final String NEGATIVE_AMOUNT = "Amount value has to be more than 0";
    public static final String NOT_ENOUGH_BALANCE = "Not enough balance";
    public static final String ROLL_NOT_ASSIGNED = "This bet has no Roll assigned!";
    public static final String NUMBER_OUT_OF_ROULETTE_RANGE = "The number is out of roulette range!";
    public static final String FIELD_IS_NULL = "Field is null";
    public static final String NO_AUTHENTICATED_USER = "There is no authenticated user";
    public static final String ILLEGAL_STATUS = "Failed status can not be set manually";

    private ErrorMessages() {
    }
}
