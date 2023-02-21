import { createReducer } from '@reduxjs/toolkit';
import {
  clearUserData,
  reduceBalance,
  setIsUserLoggedIn,
  setUserData
} from '../actions/userActions';
import { RootState } from '../store';

const initialState: UserState = {
  isLoggedIn: false,
  balance: 0
};

const userReducer = createReducer(initialState, (builder) => {
  builder
    .addCase(setUserData, (state, action) => {
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
    .addCase(clearUserData, () => initialState)
    .addCase(reduceBalance, (state, action) => {
      return { ...state, balance: state.balance - action.payload };
    });
});

export interface UserState {
  isLoggedIn: boolean;
  balance: number;
}

export interface UserDataDto {
  balance: number;
}

// Selectors
export const selectIsLoggedIn = (state: RootState) => state.user.isLoggedIn;
export const selectUserBalance = (state: RootState) => state.user.balance;

export default userReducer;
