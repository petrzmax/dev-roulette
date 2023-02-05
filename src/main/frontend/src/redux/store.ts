import { configureStore } from '@reduxjs/toolkit';
import { useDispatch } from 'react-redux';
import createSagaMiddleware from 'redux-saga';
import botsReducer from './reducers/botsReducer';
import rouletteReducer from './reducers/rouletteReducer';
import sessionReducer from './reducers/sessionReducer';
import { watcherSaga } from './sagas/rootSaga';

const sagaMiddleware = createSagaMiddleware();

const reducer = {
  session: sessionReducer,
  roulette: rouletteReducer,
  bots: botsReducer
};

const store = configureStore({
  reducer,
  middleware: [sagaMiddleware],
  devTools: process.env.NODE_ENV !== 'production'
});

sagaMiddleware.run(watcherSaga);

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;
export const useAppDispatch: () => AppDispatch = useDispatch;

export default store;
