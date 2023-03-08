import { createReducer } from '@reduxjs/toolkit';
import { clearUserData, reduceBalance, setIsUserLoggedIn, setUserData } from './actions';
import { BotDto } from './bots/bot.model';

const initialState: UserState = {
  isLoggedIn: false,
  role: '',
  balance: 0,
  bots: []
};

const userReducer = createReducer(initialState, (builder) => {
  builder
    .addCase(setUserData, (state, action) => {
      return {
        ...state,
        role: action.payload.role,
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
  role: string;
  balance: number;
  bots: BotDto[];
}

export default userReducer;
