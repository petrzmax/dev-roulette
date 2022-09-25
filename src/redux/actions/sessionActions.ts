import { createAction } from "@reduxjs/toolkit";
import { sessionDto } from "../reducers/sessionReducer";
import { ActionTypes } from "./../constants/actionTypes";

export const setSessionState = createAction<sessionDto>(ActionTypes.SET_SESSION_STATE);
