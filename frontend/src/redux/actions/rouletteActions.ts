import { createAction } from '@reduxjs/toolkit';
import { RouletteActionTypes } from './../constants/actionTypes';
import { BetDto, RouletteDto } from './../reducers/rouletteReducer';

export const setRouletteState = createAction<RouletteDto>(RouletteActionTypes.SET_ROULETTE_STATE);
export const fetchRouletteState = createAction(RouletteActionTypes.FETCH_ROULETTE_STATE);
export const postRouletteBet = createAction<BetDto>(RouletteActionTypes.POST_ROULETTE_BET);
