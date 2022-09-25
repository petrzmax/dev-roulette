import { createAction } from "@reduxjs/toolkit";
import { ActionTypes } from "./../constants/actionTypes";
import { rouletteDto } from "./../reducers/rouletteReducer";

export const setRouletteState = createAction<rouletteDto>(
  ActionTypes.SET_ROULETTE_STATE
);
