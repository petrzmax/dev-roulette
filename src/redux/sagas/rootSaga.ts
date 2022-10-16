import { takeEvery } from "redux-saga/effects";
import { ActionTypes } from "../constants/actionTypes";
import {
  handleFetchRouletteState,
  handleFetchSession,
  handlePostRouletteBet,
} from "./handlers";

export function* watcherSaga() {
  yield takeEvery(ActionTypes.FETCH_SESSION, handleFetchSession);
  yield takeEvery(ActionTypes.FETCH_ROULETTE_STATE, handleFetchRouletteState);
  yield takeEvery(ActionTypes.POST_ROULETTE_BET, handlePostRouletteBet);
}
