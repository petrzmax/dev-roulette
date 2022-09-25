import { createReducer } from "@reduxjs/toolkit";
import { setSessionState } from "../actions/sessionActions";

const initialState: sessionState = {
  token: "",
  balance: 0,
};

const sessionReducer = createReducer(initialState, (builder) => {
  builder.addCase(setSessionState, (state, action) => {
    return {
      ...state,
      token: action.payload.token,
      balance: action.payload.balance,
    };
  });
});

export interface sessionState {
  token: string;
  balance: number;
}

export interface sessionDto {
  token: string;
  balance: number;
}

export default sessionReducer;
