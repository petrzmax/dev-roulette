import { configureStore } from '@reduxjs/toolkit';
import { useDispatch } from 'react-redux';
import createSagaMiddleware from 'redux-saga';
import { watcherSaga } from './rootSaga';
import rouletteReducer from './roulette/reducer';
import userReducer from './user/reducer';

const sagaMiddleware = createSagaMiddleware();

const reducer = {
  roulette: rouletteReducer,
  user: userReducer
};

const store = configureStore({
  reducer,
  middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(sagaMiddleware),
  devTools: import.meta.env.DEV
});

sagaMiddleware.run(watcherSaga);

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;
export const useAppDispatch: () => AppDispatch = useDispatch;

export default store;
