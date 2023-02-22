import { PayloadAction } from '@reduxjs/toolkit';
import axios, { AxiosResponse } from 'axios';
import { call, put } from 'redux-saga/effects';
import Cookies from 'universal-cookie';
import { ACCESS_TOKEN_COOKIE_NAME } from '../../common/constants';
import { BotDto } from '../user/reducer';
import { setBots } from './../actions/botsActions';
import { requestFetchBots } from './requests';

// TODO Display error in meaningful way
export function handleAxiosError(error: unknown): void {
  if (axios.isAxiosError(error)) {
    console.log(error.response);
    return;
  }
  console.log(error);
}

export function* handleSetAccessTokenInCookie(action: PayloadAction<string>) {
  const cookies = new Cookies();
  // const decodedToken: GoogleToken = jwt_decode(action.payload);

  // TODO security & expiration handling
  cookies.set(ACCESS_TOKEN_COOKIE_NAME, action.payload, {
    maxAge: 60 * 60
  });
  yield;
}

export function* handleFetchBots() {
  try {
    const response: AxiosResponse<BotDto[]> = yield call(requestFetchBots);
    yield put(setBots(response.data));
  } catch (error) {
    handleAxiosError(error);
  }
}
