import { takeEvery } from 'redux-saga/effects';
import { RouletteActionTypes, SessionActionTypes } from '../constants/actionTypes';
import { BotsActionTypes } from './../constants/actionTypes';
import {
  handleFetchBots,
  handleFetchRouletteState,
  handleFetchSession,
  handleLogin,
  handleLogout,
  handlePostRouletteBet,
  handleSetAccessTokenInCookie
} from './handlers';

export function* watcherSaga() {
  // Session
  yield takeEvery(SessionActionTypes.FETCH_SESSION, handleFetchSession);
  yield takeEvery(SessionActionTypes.SET_ACCESS_TOKEN_IN_COOKIE, handleSetAccessTokenInCookie);
  yield takeEvery(SessionActionTypes.LOGIN, handleLogin);
  yield takeEvery(SessionActionTypes.LOGOUT, handleLogout);

  // Bots
  yield takeEvery(BotsActionTypes.FETCH_BOTS, handleFetchBots);

  // Roulette
  yield takeEvery(RouletteActionTypes.FETCH_ROULETTE_STATE, handleFetchRouletteState);
  yield takeEvery(RouletteActionTypes.POST_ROULETTE_BET, handlePostRouletteBet);
}
