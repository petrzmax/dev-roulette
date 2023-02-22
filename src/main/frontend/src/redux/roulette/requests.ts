import axios from 'axios';
import { rouletteBetUrl, rouletteUrl } from '../../common/endpoints';
import { getAuthenticatedHeader, headers } from '../sagas/requests';
import { BetDto } from './reducer';

export function requestFetchRouletteData() {
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
