import axios, { AxiosRequestHeaders } from 'axios';
import Cookies from 'universal-cookie';
import { ACCESS_TOKEN_COOKIE_NAME } from '../../common/constants';
import { rollHistoryUrl, rouletteBetUrl, rouletteUrl, sessionUrl } from '../../common/endpoints';
import { BetDto } from './../reducers/rouletteReducer';

export function requestFetchSession() {
  return axios.request({
    method: 'get',
    headers: getAuthenticatedHeader(),
    url: sessionUrl
  });
}

export function requestFetchRouletteState() {
  return axios.request({
    method: 'get',
    headers: headers,
    url: rouletteUrl
  });
}

export function requestPostRouletteBet(betDto: BetDto) {
  return axios.request({
    method: 'post',
    headers: getAuthenticatedHeader(),
    url: rouletteBetUrl,
    data: betDto
  });
}

export function requestFetchRollHistory() {
  return axios.request({
    method: 'get',
    headers: headers,
    url: rollHistoryUrl
  });
}

const headers: AxiosRequestHeaders = {
  'Content-Type': 'application/json',
  'Access-Control-Allow-Origin': '*'
};

function getAuthenticatedHeader(): AxiosRequestHeaders {
  const cookies = new Cookies();
  const token = cookies.get(ACCESS_TOKEN_COOKIE_NAME);
  return token ? { ...headers, Authorization: 'Bearer ' + token } : headers;
}
