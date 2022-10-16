import { createAction } from "@reduxjs/toolkit";
import { ActionTypes } from "./../constants/actionTypes";
import { BetDto, RouletteDto } from "./../reducers/rouletteReducer";

export const setRouletteState = createAction<RouletteDto>(
  ActionTypes.SET_ROULETTE_STATE
);

export const fetchRouletteState = createAction(
  ActionTypes.FETCH_ROULETTE_STATE
);

export const postRouletteBet = createAction<BetDto>(
  ActionTypes.POST_ROULETTE_BET
);
