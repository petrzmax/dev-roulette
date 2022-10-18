import { createReducer } from '@reduxjs/toolkit';
import { setSession } from '../actions/sessionActions';
import { reduceBalance } from './../actions/sessionActions';

const initialState: SessionState = {
  token: '',
  balance: 0
};

const sessionReducer = createReducer(initialState, (builder) => {
  builder
    .addCase(setSession, (state, action) => {
      return {
        ...state,
        token: action.payload.token,
        balance: action.payload.balance
      };
    })
    .addCase(reduceBalance, (state, action) => {
      return { ...state, balance: state.balance - action.payload };
    });
});

export interface SessionState {
  token: string;
  balance: number;
}

export interface SessionDto {
  token: string;
  balance: number;
}

export default sessionReducer;
