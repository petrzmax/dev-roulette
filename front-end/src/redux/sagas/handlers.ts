import { CredentialResponse } from '@react-oauth/google';
import { PayloadAction } from '@reduxjs/toolkit';
import axios, { AxiosResponse } from 'axios';
import { call, put } from 'redux-saga/effects';
import Cookies from 'universal-cookie';
import { ACCESS_TOKEN_COOKIE_NAME } from '../../common/constants';
import { setRouletteState } from '../actions/rouletteActions';
import { BetDto, RouletteDto } from '../reducers/rouletteReducer';
import {
  clearSession,
  fetchSession,
  reduceBalance,
  setAccessTokenInCookie,
  setIsUserLoggedIn,
  setSession
} from './../actions/sessionActions';
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

export function* handleSetAccessTokenInCookie(action: PayloadAction<string>) {
  const cookies = new Cookies();
  // const decodedToken: GoogleToken = jwt_decode(action.payload);

  // TODO security & expiration handling
  cookies.set(ACCESS_TOKEN_COOKIE_NAME, action.payload, {
    maxAge: 60 * 60
  });
  yield;
}

export function* handleLogin(action: PayloadAction<CredentialResponse>) {
  if (action.payload.credential) {
    yield put(setAccessTokenInCookie(action.payload.credential));
    yield put(setIsUserLoggedIn(true));
    yield put(fetchSession());
  }
}

export function* handleLogout() {
  const cookies = new Cookies();
  cookies.remove(ACCESS_TOKEN_COOKIE_NAME);
  yield put(clearSession());
}

// TODO move to better suiting place if it will be needed
interface GoogleToken {
  aud: string;
  azp: string;
  email: string;
  email_verified: boolean;
  exp: number;
  family_name: string;
  given_name: string;
  iat: number;
  iss: string;
  jti: string;
  name: string;
  nbf: number;
  picture: string;
  sub: string;
}
