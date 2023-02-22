import { CredentialResponse } from '@react-oauth/google';
import { PayloadAction } from '@reduxjs/toolkit';
import { AxiosResponse } from 'axios';
import { call, put } from 'redux-saga/effects';
import Cookies from 'universal-cookie';
import { ACCESS_TOKEN_COOKIE_NAME } from '../../common/constants';
import { clearBots, fetchBots } from '../actions/botsActions';
import { handleAxiosError } from '../sagas/handlers';
import {
  clearUserData,
  fetchUserData,
  setAccessTokenInCookie,
  setIsUserLoggedIn,
  setUserData
} from './actions';
import { UserDataDto } from './reducer';
import { requestFetchUserData } from './requests';

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
    yield put(fetchBots());
  }
}

export function* handleLogout() {
  const cookies = new Cookies();
  cookies.remove(ACCESS_TOKEN_COOKIE_NAME);
  yield put(clearUserData());
  yield put(clearBots());
}
