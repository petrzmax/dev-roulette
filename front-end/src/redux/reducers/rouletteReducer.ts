import { createReducer } from '@reduxjs/toolkit';
import { RootState } from '../store';
import { BetType } from './../../common/constants';
import { setRouletteState } from './../actions/rouletteActions';

const initialState: RouletteState = {
  rollHistory: [],
  tileCoverageFactor: 0,
  nextRollTimeStamp: ''
};

const rouletteReducer = createReducer(initialState, (builder) => {
  builder
    .addCase(setRouletteState, (state, action) => {
      return {
        ...state,
        rollHistory: action.payload.rollHistory,
        tileCoverageFactor: action.payload.tileCoverageFactor,
        nextRollTimeStamp: action.payload.nextRollTimeStamp
      };
    })
    .addDefaultCase(() => undefined);
});

export interface RouletteState {
  rollHistory: number[];
  tileCoverageFactor: number;
  nextRollTimeStamp: string;
}

export interface RouletteDto {
  rollHistory: number[];
  tileCoverageFactor: number;
  nextRollTimeStamp: string;
}

export interface BetDto {
  betType: BetType;
  amount: number;
}

// Selectors
export const selectRollHistory = (state: RootState) => state.roulette.rollHistory;
export const selectLastRoll = (state: RootState) => state.roulette.rollHistory.slice(-1)[0];
export const selectNextRollTimeStamp = (state: RootState) => state.roulette.nextRollTimeStamp;
export const selectTileCoverageFactor = (state: RootState) => state.roulette.tileCoverageFactor;

export default rouletteReducer;
