import { createAction } from '@reduxjs/toolkit';
import BotActionTypes from './actionTypes';
import { BotCreationDto } from './bot.model';
import { BotDto } from './bot.model.d';

export const createBot = createAction<BotCreationDto>(BotActionTypes.CREATE_BOT);
export const addBotToStore = createAction<BotDto>(BotActionTypes.ADD_BOT_TO_STORE);

export const deleteBot = createAction<number>(BotActionTypes.DELETE_BOT);
export const deleteBotFromStore = createAction<number>(BotActionTypes.DELETE_BOT_FROM_STORE);
