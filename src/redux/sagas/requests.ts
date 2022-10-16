import axios from "axios";
import {
  rollHistoryUrl,
  rouletteBetUrl,
  rouletteUrl,
  sessionUrl,
} from "../../common/endpoints";
import { BetDto } from "./../reducers/rouletteReducer";

export function requestFetchSession() {
  return axios.request({
    method: "get",
    url: sessionUrl,
  });
}

export function requestFetchRouletteState() {
  return axios.request({
    method: "get",
    url: rouletteUrl,
  });
}

export function requestPostRouletteBet(betDto: BetDto) {
  return axios.request({
    method: "post",
    url: rouletteBetUrl,
    data: betDto,
  });
}

export function requestFetchRollHistory() {
  return axios.request({
    method: "get",
    url: rollHistoryUrl,
  });
}
