import { takeEvery } from 'redux-saga/effects';
import { RouletteActionTypes, UserActionTypes } from '../constants/actionTypes';
import { BotsActionTypes } from './../constants/actionTypes';
import {
  handleFetchBots,
  handleFetchRouletteState,
  handleFetchUserData,
  handleLogin,
  handleLogout,
  handlePostRouletteBet,
  handleSetAccessTokenInCookie
} from './handlers';

export function* watcherSaga() {
  // User
  yield takeEvery(UserActionTypes.FETCH_USER_DATA, handleFetchUserData);
  yield takeEvery(UserActionTypes.SET_ACCESS_TOKEN_IN_COOKIE, handleSetAccessTokenInCookie);
  yield takeEvery(UserActionTypes.LOGIN, handleLogin);
  yield takeEvery(UserActionTypes.LOGOUT, handleLogout);

  // Bots
  yield takeEvery(BotsActionTypes.FETCH_BOTS, handleFetchBots);

  // Roulette
  yield takeEvery(RouletteActionTypes.FETCH_ROULETTE_STATE, handleFetchRouletteState);
  yield takeEvery(RouletteActionTypes.POST_ROULETTE_BET, handlePostRouletteBet);
}
