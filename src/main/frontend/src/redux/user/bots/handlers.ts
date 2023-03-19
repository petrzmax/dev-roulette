import { PayloadAction } from '@reduxjs/toolkit';
import { AxiosResponse } from 'axios';
import toast from 'react-hot-toast';
import { call, put } from 'redux-saga/effects';
import { BOT_CREATED, BOT_SCRIPT_UPDATED } from '../../../common/messages';
import { handleAxiosError } from '../../common/requestErrorHandler';
import { BOT_DELETED, BOT_STATUS_UPDATED } from './../../../common/messages';
import { addBotToStore, deleteBotFromStore, updateBotInStore } from './actions';
import { BotCreationDto } from './bot.model';
import { BotDto, BotScriptDto, BotStatusDto } from './bot.model.d';
import {
  requestCreateBot,
  requestDeleteBot,
  requestUpdateBotScript,
  requestUpdateBotStatus
} from './requests';

export function* handleCreateBot(action: PayloadAction<BotCreationDto, string>) {
  try {
    const response: AxiosResponse<BotDto> = yield call(requestCreateBot, action.payload);
    yield put(addBotToStore(response.data));
    toast.success(BOT_CREATED);
  } catch (error) {
    handleAxiosError(error);
  }
}

export function* handleDeleteBot(action: PayloadAction<number, string>) {
  try {
    yield call(requestDeleteBot, action.payload);
    yield put(deleteBotFromStore(action.payload));
    toast.success(BOT_DELETED);
  } catch (error) {
    handleAxiosError(error);
  }
}

export function* handleUpdateBotScript(action: PayloadAction<BotScriptDto, string>) {
  try {
    const response: AxiosResponse<BotDto> = yield call(requestUpdateBotScript, action.payload);
    yield put(updateBotInStore(response.data));
    toast.success(BOT_SCRIPT_UPDATED);
  } catch (error) {
    handleAxiosError(error);
  }
}

export function* handleUpdateBotStatus(action: PayloadAction<BotStatusDto, string>) {
  try {
    const response: AxiosResponse<BotDto> = yield call(requestUpdateBotStatus, action.payload);
    yield put(updateBotInStore(response.data));
    toast.success(BOT_STATUS_UPDATED);
  } catch (error) {
    handleAxiosError(error);
  }
}
