import { createAction } from "@reduxjs/toolkit";
import { SessionDto } from "../reducers/sessionReducer";
import { ActionTypes } from "./../constants/actionTypes";

export const fetchSession = createAction(ActionTypes.FETCH_SESSION);
export const setSession = createAction<SessionDto>(ActionTypes.SET_SESSION);
export const reduceBalance = createAction<number>(ActionTypes.REDUCE_BALANCE);
