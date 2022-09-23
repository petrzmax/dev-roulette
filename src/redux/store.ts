import { configureStore } from "@reduxjs/toolkit";
import { useDispatch } from "react-redux";
import { rouletteReducer } from "./reducers/rouletteReducer";

const reducer = {
  roulette: rouletteReducer,
};

const store = configureStore({
  reducer,
  devTools: process.env.NODE_ENV !== "production",
});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;
export const useAppDispatch: () => AppDispatch = useDispatch;

export default store;
