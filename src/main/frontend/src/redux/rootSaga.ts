import { takeEvery } from 'redux-saga/effects';
import AdminActionTypes from './admin/actionTypes';
import { handleDispatchMessage } from './admin/handlers';
import RouletteActionTypes from './roulette/actionTypes';
import { handleFetchRouletteData, handlePostRouletteBet } from './roulette/handlers';
import UserActionTypes from './user/actionTypes';
import BotActionTypes from './user/bots/actionTypes';
import { handleCreateBot } from './user/bots/handlers';
import {
  handleFetchUserData,
  handleLogin,
  handleLogout,
  handleSetAccessTokenInCookie
} from './user/handlers';

export function* watcherSaga() {
  // User
  yield takeEvery(UserActionTypes.FETCH_USER_DATA, handleFetchUserData);
  yield takeEvery(UserActionTypes.SET_ACCESS_TOKEN_IN_COOKIE, handleSetAccessTokenInCookie);
  yield takeEvery(UserActionTypes.LOGIN, handleLogin);
  yield takeEvery(UserActionTypes.LOGOUT, handleLogout);

  // Roulette
  yield takeEvery(RouletteActionTypes.FETCH_ROULETTE_DATA, handleFetchRouletteData);
  yield takeEvery(RouletteActionTypes.POST_ROULETTE_BET, handlePostRouletteBet);

  // Bots
  yield takeEvery(BotActionTypes.CREATE_BOT, handleCreateBot);

  // Admin
  yield takeEvery(AdminActionTypes.DISPATCH_MESSAGE, handleDispatchMessage);
}
