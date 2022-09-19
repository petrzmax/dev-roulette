import { createAction } from "@reduxjs/toolkit";
import { ActionTypes } from "./../constants/actionTypes";

export const getSession = createAction(ActionTypes.GET_SESSION);
export const getRouletteState = createAction(ActionTypes.GET_ROULETTE_STATE);
