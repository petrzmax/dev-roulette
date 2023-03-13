import { createReducer } from '@reduxjs/toolkit';
import { clearUserData, reduceBalance, setIsUserLoggedIn, setUserData } from './actions';
import { addBotToStore, deleteBotFromStore, patchBotInStore } from './bots/actions';
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
    .addCase(addBotToStore, (state, action) => {
      return { ...state, bots: [...state.bots, action.payload] };
    })
    .addCase(deleteBotFromStore, (state, action) => {
      return { ...state, bots: [...state.bots.filter((b) => b.id !== action.payload)] };
    })
    .addCase(patchBotInStore, (state, action) => {
      return {
        ...state,
        bots: [
          ...state.bots.map((bot) =>
            bot.id === action.payload.id ? { ...bot, scriptBody: action.payload.scriptBody } : bot
          )
        ]
      };
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
