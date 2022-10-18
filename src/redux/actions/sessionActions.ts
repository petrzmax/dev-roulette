import { createAction } from '@reduxjs/toolkit';
import { SessionDto } from '../reducers/sessionReducer';
import { SessionActionTypes } from './../constants/actionTypes';

export const fetchSession = createAction(SessionActionTypes.FETCH_SESSION);
export const setSession = createAction<SessionDto>(SessionActionTypes.SET_SESSION);
export const reduceBalance = createAction<number>(SessionActionTypes.REDUCE_BALANCE);
