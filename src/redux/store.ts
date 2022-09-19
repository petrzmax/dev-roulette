import { configureStore } from "@reduxjs/toolkit";
import { rouletteReducer } from "./reducers/rouletteReducer";

const reducer = {
  roulette: rouletteReducer,
};

const store = configureStore({
  reducer,
  devTools: process.env.NODE_ENV !== "production",
});

export default store;
