import axios from 'axios';
import { userUrl } from '../../common/endpoints';
import { getAuthenticatedHeader } from '../common/requestHeaders';

export function requestFetchUserData() {
  return axios.request({
    method: 'get',
    headers: getAuthenticatedHeader(),
    url: userUrl
  });
}
