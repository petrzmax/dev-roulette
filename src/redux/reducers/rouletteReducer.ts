import { createReducer } from "@reduxjs/toolkit";
import {
  getSession,
  setRouletteState,
  setTileCoverageFactor,
} from "./../actions/rouletteActions";

const initialState: rouletteState = {
  rollHistory: [],
  timeToNextRoll: 0,
  tileCoverageFactor: 0,
};

export const rouletteReducer = createReducer(initialState, (builder) => {
  builder
    .addCase(getSession, (state, action) => {
      state.timeToNextRoll++;
    })
    .addCase(setRouletteState, (state, action) => {
      return {
        ...state,
        rollHistory: action.payload.rollHistory,
        timeToNextRoll: action.payload.timeToRoll,
      };
    })
    .addCase(setTileCoverageFactor, (state, action) => {
      return {
        ...state,
        tileCoverageFactor: action.payload,
      };
    })
    .addDefaultCase((state, action) => {});
});

export interface rouletteState {
  rollHistory: number[];
  timeToNextRoll: number;
  tileCoverageFactor: number;
}

export interface rouletteDto {
  rollHistory: number[];
  timeToRoll: number;
}
