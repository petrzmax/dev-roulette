import { createAction } from '@reduxjs/toolkit';
import BotActionTypes from './actionTypes';
import { BotCreationDto } from './bot.model';

export const createBot = createAction<BotCreationDto>(BotActionTypes.CREATE_BOT);
