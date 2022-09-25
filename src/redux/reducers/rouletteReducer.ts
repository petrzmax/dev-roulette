import { createReducer } from "@reduxjs/toolkit";
import { setRouletteState } from "./../actions/rouletteActions";

const initialState: rouletteState = {
  rollHistory: [],
  tileCoverageFactor: 0,
  nextRollTimeStamp: 0,
};

export const rouletteReducer = createReducer(initialState, (builder) => {
  builder
    .addCase(setRouletteState, (state, action) => {
      return {
        ...state,
        rollHistory: action.payload.rollHistory,
        tileCoverageFactor: action.payload.tileCoverageFactor,
        nextRollTimeStamp: action.payload.nextRollTimeStamp,
      };
    })
    .addDefaultCase((state, action) => {});
});

export interface rouletteState {
  rollHistory: number[];
  tileCoverageFactor: number;
  nextRollTimeStamp: number;
}

export interface rouletteDto {
  rollHistory: number[];
  tileCoverageFactor: number;
  nextRollTimeStamp: number;
}
