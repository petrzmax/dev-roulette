import { CredentialResponse } from '@react-oauth/google';
import { createAction } from '@reduxjs/toolkit';
import { SessionDto } from '../reducers/sessionReducer';
import { SessionActionTypes } from './../constants/actionTypes';

export const fetchSession = createAction(SessionActionTypes.FETCH_SESSION);
export const setSession = createAction<SessionDto>(SessionActionTypes.SET_SESSION);
export const setIsUserLoggedIn = createAction<boolean>(SessionActionTypes.SET_IS_USER_LOGGED_IN);
export const logout = createAction(SessionActionTypes.LOGOUT);
export const clearSession = createAction(SessionActionTypes.CLEAR_SESSION);
export const login = createAction<CredentialResponse>(SessionActionTypes.LOGIN);
export const setAccessTokenInCookie = createAction<string>(
  SessionActionTypes.SET_ACCESS_TOKEN_IN_COOKIE
);

export const reduceBalance = createAction<number>(SessionActionTypes.REDUCE_BALANCE);
