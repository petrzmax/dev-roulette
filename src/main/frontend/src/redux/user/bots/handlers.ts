import { PayloadAction } from '@reduxjs/toolkit';
import { AxiosResponse } from 'axios';
import toast from 'react-hot-toast';
import { call, put } from 'redux-saga/effects';
import { BOT_CREATED, BOT_PATCHED } from '../../../common/messages';
import { handleAxiosError } from '../../common/requestErrorHandler';
import { BOT_DELETED } from './../../../common/messages';
import { addBotToStore, deleteBotFromStore, patchBotInStore } from './actions';
import { BotCreationDto } from './bot.model';
import { BotDto, BotPatchDto } from './bot.model.d';
import { requestCreateBot, requestDeleteBot, requestPatchBotScript } from './requests';

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

export function* handlePatchBotScript(action: PayloadAction<BotPatchDto, string>) {
  try {
    yield call(requestPatchBotScript, action.payload);
    yield put(patchBotInStore(action.payload));
    toast.success(BOT_PATCHED);
  } catch (error) {
    handleAxiosError(error);
  }
}
