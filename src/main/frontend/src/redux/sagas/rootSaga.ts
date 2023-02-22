import { takeEvery } from 'redux-saga/effects';
import { BotsActionTypes } from '../actionTypes';
import RouletteActionTypes from '../roulette/actionTypes';
import { handleFetchRouletteData, handlePostRouletteBet } from '../roulette/handlers';
import UserActionTypes from '../user/actionTypes';
import { handleFetchUserData, handleLogin, handleLogout } from '../user/handlers';
import { handleFetchBots, handleSetAccessTokenInCookie } from './handlers';

export function* watcherSaga() {
  // User
  yield takeEvery(UserActionTypes.FETCH_USER_DATA, handleFetchUserData);
  yield takeEvery(UserActionTypes.SET_ACCESS_TOKEN_IN_COOKIE, handleSetAccessTokenInCookie);
  yield takeEvery(UserActionTypes.LOGIN, handleLogin);
  yield takeEvery(UserActionTypes.LOGOUT, handleLogout);

  // Bots
  yield takeEvery(BotsActionTypes.FETCH_BOTS, handleFetchBots);

  // Roulette
  yield takeEvery(RouletteActionTypes.FETCH_ROULETTE_DATA, handleFetchRouletteData);
  yield takeEvery(RouletteActionTypes.POST_ROULETTE_BET, handlePostRouletteBet);
}
