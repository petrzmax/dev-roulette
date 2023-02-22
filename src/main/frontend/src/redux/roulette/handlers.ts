import { PayloadAction } from '@reduxjs/toolkit';
import { AxiosResponse } from 'axios';
import { call, put } from 'redux-saga/effects';
import { handleAxiosError } from '../common/requestErrorHandler';
import { reduceBalance } from '../user/actions';
import { setRouletteData } from './actions';
import { BetDto, RouletteDto } from './reducer';
import { requestFetchRouletteData, requestPostRouletteBet } from './requests';

export function* handleFetchRouletteData() {
  try {
    const response: AxiosResponse<RouletteDto> = yield call(requestFetchRouletteData);
    yield put(setRouletteData(response.data));
  } catch (error) {
    handleAxiosError(error);
  }
}

export function* handlePostRouletteBet(action: PayloadAction<BetDto, string>) {
  try {
    yield call(requestPostRouletteBet, action.payload);
    yield put(reduceBalance(action.payload.amount));
  } catch (error) {
    handleAxiosError(error);
  }
}
