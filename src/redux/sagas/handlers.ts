import { PayloadAction } from '@reduxjs/toolkit';
import axios, { AxiosResponse } from 'axios';
import { call, put } from 'redux-saga/effects';
import { setRouletteState } from '../actions/rouletteActions';
import { BetDto, RouletteDto } from '../reducers/rouletteReducer';
import { reduceBalance, setSession } from './../actions/sessionActions';
import { SessionDto } from './../reducers/sessionReducer';
import { requestFetchRouletteState, requestFetchSession, requestPostRouletteBet } from './requests';

export function* handleFetchRouletteState() {
  try {
    const response: AxiosResponse<RouletteDto> = yield call(requestFetchRouletteState);
    yield put(setRouletteState(response.data));
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

export function* handleFetchSession() {
  try {
    const response: AxiosResponse<SessionDto> = yield call(requestFetchSession);
    yield put(setSession(response.data));
  } catch (error) {
    handleAxiosError(error);
  }
}

function handleAxiosError(error: unknown): void {
  if (axios.isAxiosError(error)) {
    console.log(error.response);
    return;
  }
  console.log(error);
}
