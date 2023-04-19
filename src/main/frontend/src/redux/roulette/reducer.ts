import { createReducer } from '@reduxjs/toolkit';
import { pushLastRollHistory, setRollEventData, setRouletteData } from './actions';

const initialState: RouletteState = {
  lastRoll: 0,
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
    .addCase(setRollEventData, (state, action) => {
      return {
        ...state,
        lastRoll: action.payload.result,
        tileCoverageFactor: action.payload.tileCoverageFactor,
        nextRollTimeStamp: action.payload.nextRollTimeStamp
      };
    })
    .addCase(pushLastRollHistory, (state) => {
      return {
        ...state,
        rollHistory: [...state.rollHistory.slice(1), state.lastRoll]
      };
    })
    .addDefaultCase(() => undefined);
});

export interface RouletteState {
  lastRoll: number;
  rollHistory: number[];
  tileCoverageFactor: number;
  nextRollTimeStamp: string;
}

export default rouletteReducer;
