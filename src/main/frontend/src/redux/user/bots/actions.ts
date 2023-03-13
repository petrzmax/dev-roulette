import { createAction } from '@reduxjs/toolkit';
import BotActionTypes from './actionTypes';
import { BotCreationDto } from './bot.model';
import { BotDto, BotPatchDto } from './bot.model.d';

export const createBot = createAction<BotCreationDto>(BotActionTypes.CREATE_BOT);
export const addBotToStore = createAction<BotDto>(BotActionTypes.ADD_BOT_TO_STORE);

export const deleteBot = createAction<number>(BotActionTypes.DELETE_BOT);
export const deleteBotFromStore = createAction<number>(BotActionTypes.DELETE_BOT_FROM_STORE);

export const patchBotScript = createAction<BotPatchDto>(BotActionTypes.PATCH_BOT_SCRIPT);
export const patchBotInStore = createAction<BotPatchDto>(BotActionTypes.PATCH_BOT_IN_STORE);
