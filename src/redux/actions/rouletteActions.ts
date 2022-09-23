import { rouletteDto } from './../reducers/rouletteReducer';
import { createAction } from "@reduxjs/toolkit";
import { ActionTypes } from "./../constants/actionTypes";

export const getSession = createAction(ActionTypes.GET_SESSION);
export const setRouletteState = createAction<rouletteDto>(ActionTypes.SET_ROULETTE_STATE);
export const setTileCoverageFactor = createAction<number>(ActionTypes.SET_TILE_COVERAGE_FACTOR);
