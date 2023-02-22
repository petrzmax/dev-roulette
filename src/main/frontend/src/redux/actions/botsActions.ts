import { createAction } from '@reduxjs/toolkit';
import { BotsActionTypes } from '../actionTypes';
import { BotDto } from '../user/reducer';

export const fetchBots = createAction(BotsActionTypes.FETCH_BOTS);
export const setBots = createAction<BotDto[]>(BotsActionTypes.SET_BOTS);
export const clearBots = createAction(BotsActionTypes.CLEAR_BOTS);
