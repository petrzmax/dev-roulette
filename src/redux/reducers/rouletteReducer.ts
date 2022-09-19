import { createReducer } from "@reduxjs/toolkit";
import { getRouletteState, getSession } from "./../actions/rouletteActions";

const initialState: rouletteState = {
  lastRolls: [],
  timeToRoll: 0,
};

export const rouletteReducer = createReducer(initialState, (builder) => {
  builder
    .addCase(getSession, (state, action) => {
      state.timeToRoll++;
    })
    .addCase(getRouletteState, (state, action) => {
      state.timeToRoll++;
    });
});

export interface rouletteState {
  lastRolls: number[];
  timeToRoll: number;
}
