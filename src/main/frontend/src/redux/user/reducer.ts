import { createReducer } from '@reduxjs/toolkit';
import { RootState } from '../store';
import { clearUserData, reduceBalance, setIsUserLoggedIn, setUserData } from './actions';

const initialState: UserState = {
  isLoggedIn: false,
  balance: 0,
  bots: []
};

const userReducer = createReducer(initialState, (builder) => {
  builder
    .addCase(setUserData, (state, action) => {
      return {
        ...state,
        balance: action.payload.balance,
        bots: action.payload.bots
      };
    })
    .addCase(setIsUserLoggedIn, (state, action) => {
      return {
        ...state,
        isLoggedIn: action.payload
      };
    })
    .addCase(clearUserData, () => initialState)
    .addCase(reduceBalance, (state, action) => {
      return { ...state, balance: state.balance - action.payload };
    })
    .addDefaultCase(() => undefined);
});

export interface UserState {
  isLoggedIn: boolean;
  balance: number;
  bots: BotDto[];
}

export interface UserDataDto {
  balance: number;
  bots: BotDto[];
}

export interface BotDto {
  id: number;
  name: string;
  scriptBody: string;
  balance: number;
  enabled: boolean;
  errorMessage: string;
}

// Selectors
export const selectIsLoggedIn = (state: RootState) => state.user.isLoggedIn;
export const selectUserBalance = (state: RootState) => state.user.balance;
export const selectBots = (state: RootState) => state.user.bots;

export default userReducer;
