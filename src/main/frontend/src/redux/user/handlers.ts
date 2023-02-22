import { CredentialResponse } from '@react-oauth/google';
import { PayloadAction } from '@reduxjs/toolkit';
import { AxiosResponse } from 'axios';
import { call, put } from 'redux-saga/effects';
import Cookies from 'universal-cookie';
import { ACCESS_TOKEN_COOKIE_NAME } from '../../common/constants';
import { handleAxiosError } from '../common/requestErrorHandler';
import {
  clearUserData,
  fetchUserData,
  setAccessTokenInCookie,
  setIsUserLoggedIn,
  setUserData
} from './actions';
import { requestFetchUserData } from './requests';
import { UserDataDto } from './user.model';

export function* handleFetchUserData() {
  try {
    const response: AxiosResponse<UserDataDto> = yield call(requestFetchUserData);
    yield put(setUserData(response.data));
  } catch (error) {
    handleAxiosError(error);
  }
}

export function* handleLogin(action: PayloadAction<CredentialResponse>) {
  if (action.payload.credential) {
    yield put(setAccessTokenInCookie(action.payload.credential));
    yield put(setIsUserLoggedIn(true));
    yield put(fetchUserData());
  }
}

export function* handleLogout() {
  const cookies = new Cookies();
  cookies.remove(ACCESS_TOKEN_COOKIE_NAME);
  yield put(clearUserData());
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
