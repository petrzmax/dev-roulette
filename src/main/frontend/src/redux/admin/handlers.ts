import { PayloadAction } from '@reduxjs/toolkit';
import toast from 'react-hot-toast';
import { call } from 'redux-saga/effects';
import { MESSAGE_SENT } from '../../common/messages';
import { handleAxiosError } from '../common/requestErrorHandler';
import { MessageDto } from './admin.model';
import { requestDispatchMessage } from './requests';

export function* handleDispatchMessage(action: PayloadAction<MessageDto, string>) {
  try {
    yield call(requestDispatchMessage, action.payload);
    toast.success(MESSAGE_SENT);
  } catch (error) {
    handleAxiosError(error);
  }
}
