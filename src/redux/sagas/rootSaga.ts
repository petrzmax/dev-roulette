import { takeEvery } from 'redux-saga/effects';
import { RouletteActionTypes, SessionActionTypes } from '../constants/actionTypes';
import { handleFetchRouletteState, handleFetchSession, handlePostRouletteBet } from './handlers';

export function* watcherSaga() {
  yield takeEvery(SessionActionTypes.FETCH_SESSION, handleFetchSession);
  yield takeEvery(RouletteActionTypes.FETCH_ROULETTE_STATE, handleFetchRouletteState);
  yield takeEvery(RouletteActionTypes.POST_ROULETTE_BET, handlePostRouletteBet);
}
