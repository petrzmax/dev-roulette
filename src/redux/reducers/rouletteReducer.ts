import { createReducer } from "@reduxjs/toolkit";
import { BetType } from "./../../common/constants";
import { setRouletteState } from "./../actions/rouletteActions";

const initialState: RouletteState = {
  rollHistory: [],
  tileCoverageFactor: 0,
  nextRollTimeStamp: "",
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
