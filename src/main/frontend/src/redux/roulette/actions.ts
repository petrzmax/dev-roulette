import { createAction } from '@reduxjs/toolkit';
import RouletteActionTypes from './actionTypes';
import { BetDto, RouletteDto } from './roulette.model';

export const fetchRouletteData = createAction(RouletteActionTypes.FETCH_ROULETTE_DATA);
export const setRouletteData = createAction<RouletteDto>(RouletteActionTypes.SET_ROULETTE_DATA);
export const postRouletteBet = createAction<BetDto>(RouletteActionTypes.POST_ROULETTE_BET);
