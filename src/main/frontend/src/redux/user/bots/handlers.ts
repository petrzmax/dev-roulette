import { PayloadAction } from '@reduxjs/toolkit';
import { AxiosResponse } from 'axios';
import toast from 'react-hot-toast';
import { call, put } from 'redux-saga/effects';
import { BET_CREATED } from '../../../common/messages';
import { handleAxiosError } from '../../common/requestErrorHandler';
import { BET_DELETED } from './../../../common/messages';
import { addBotToStore, deleteBotFromStore } from './actions';
import { BotCreationDto } from './bot.model';
import { BotDto } from './bot.model.d';
import { requestCreateBot, requestDeleteBot } from './requests';

export function* handleCreateBot(action: PayloadAction<BotCreationDto, string>) {
  try {
    const response: AxiosResponse<BotDto> = yield call(requestCreateBot, action.payload);
    yield put(addBotToStore(response.data));
    toast.success(BET_CREATED);
  } catch (error) {
    handleAxiosError(error);
  }
}

export function* handleDeleteBot(action: PayloadAction<number, string>) {
  try {
    yield call(requestDeleteBot, action.payload);
    yield put(deleteBotFromStore(action.payload));
    toast.success(BET_DELETED);
  } catch (error) {
    handleAxiosError(error);
  }
}
