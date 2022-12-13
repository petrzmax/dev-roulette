import { createReducer } from '@reduxjs/toolkit';
import { clearSession, setIsUserLoggedIn, setSession } from '../actions/sessionActions';
import { RootState } from '../store';
import { reduceBalance } from './../actions/sessionActions';

const initialState: SessionState = {
  isLoggedIn: false,
  balance: 0
};

const sessionReducer = createReducer(initialState, (builder) => {
  builder
    .addCase(setSession, (state, action) => {
      return {
        ...state,
        balance: action.payload.balance
      };
    })
    .addCase(setIsUserLoggedIn, (state, action) => {
      return {
        ...state,
        isLoggedIn: action.payload
      };
    })
    .addCase(clearSession, () => initialState)
    .addCase(reduceBalance, (state, action) => {
      return { ...state, balance: state.balance - action.payload };
    });
});

export interface SessionState {
  isLoggedIn: boolean;
  balance: number;
}

export interface SessionDto {
  balance: number;
}

// Selectors
export const selectIsLoggedIn = (state: RootState) => state.session.isLoggedIn;
export const selectBalance = (state: RootState) => state.session.balance;

export default sessionReducer;
