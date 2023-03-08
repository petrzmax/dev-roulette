import { PayloadAction } from '@reduxjs/toolkit';
import toast from 'react-hot-toast';
import { call } from 'redux-saga/effects';
import { BET_CREATED } from '../../../common/messages';
import { handleAxiosError } from '../../common/requestErrorHandler';
import { BotCreationDto } from './bot.model';
import { requestCreateBot } from './requests';

export function* handleCreateBot(action: PayloadAction<BotCreationDto, string>) {
  try {
    yield call(requestCreateBot, action.payload);
    toast.success(BET_CREATED);
  } catch (error) {
    handleAxiosError(error);
  }
}
