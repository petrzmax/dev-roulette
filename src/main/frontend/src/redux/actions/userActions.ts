import { CredentialResponse } from '@react-oauth/google';
import { createAction } from '@reduxjs/toolkit';
import { UserActionTypes } from '../constants/actionTypes';
import { UserDataDto } from '../reducers/userReducer';

export const fetchUserData = createAction(UserActionTypes.FETCH_USER_DATA);
export const setUserData = createAction<UserDataDto>(UserActionTypes.SET_USER_DATA);
export const clearUserData = createAction(UserActionTypes.CLEAR_USER_DATA);

export const login = createAction<CredentialResponse>(UserActionTypes.LOGIN);
export const logout = createAction(UserActionTypes.LOGOUT);

export const setIsUserLoggedIn = createAction<boolean>(UserActionTypes.SET_IS_USER_LOGGED_IN);
export const setAccessTokenInCookie = createAction<string>(
  UserActionTypes.SET_ACCESS_TOKEN_IN_COOKIE
);

export const reduceBalance = createAction<number>(UserActionTypes.REDUCE_BALANCE);
