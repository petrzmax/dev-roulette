import { createAction } from '@reduxjs/toolkit';
import { BotsActionTypes } from './../constants/actionTypes';
import { BotDto } from './../reducers/botsReducer';

export const fetchBots = createAction(BotsActionTypes.FETCH_BOTS);
export const setBots = createAction<BotDto[]>(BotsActionTypes.SET_BOTS);
