import { createAction } from '@reduxjs/toolkit';
import { BotCreationDto } from './../user.model.d';
import BotActionTypes from './actionTypes';

export const createBot = createAction<BotCreationDto>(BotActionTypes.CREATE_BOT);
