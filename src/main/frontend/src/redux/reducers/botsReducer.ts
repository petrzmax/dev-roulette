import { createReducer } from '@reduxjs/toolkit';
import { RootState } from '../store';
import { setBots } from './../actions/botsActions';

const initialState: BotsState = {
  bots: []
};

const botsReducer = createReducer(initialState, (builder) => {
  builder
    .addCase(setBots, (state, action) => {
      return {
        ...state,
        bots: action.payload
      };
    })
    .addDefaultCase(() => undefined);
});

export interface BotsState {
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
export const selectBots = (state: RootState) => state.bots.bots;

export default botsReducer;
