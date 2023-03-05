import { createAction } from '@reduxjs/toolkit';
import { RollEventDto } from './../../events/events.model.d';
import RouletteActionTypes from './actionTypes';
import { BetDto, RouletteDto } from './roulette.model';

export const fetchRouletteData = createAction(RouletteActionTypes.FETCH_ROULETTE_DATA);
export const setRouletteData = createAction<RouletteDto>(RouletteActionTypes.SET_ROULETTE_DATA);
export const setRollEventData = createAction<RollEventDto>(RouletteActionTypes.SET_ROLL_EVENT_DATA);
export const pushLastRollHistory = createAction(RouletteActionTypes.PUSH_LAST_ROLL_TO_HISTORY);
export const postRouletteBet = createAction<BetDto>(RouletteActionTypes.POST_ROULETTE_BET);
