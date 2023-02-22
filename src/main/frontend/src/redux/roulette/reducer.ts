import { createReducer } from '@reduxjs/toolkit';
import { setRouletteData } from './actions';

const initialState: RouletteState = {
  rollHistory: [],
  tileCoverageFactor: 0,
  nextRollTimeStamp: ''
};

const rouletteReducer = createReducer(initialState, (builder) => {
  builder
    .addCase(setRouletteData, (state, action) => {
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

export default rouletteReducer;
