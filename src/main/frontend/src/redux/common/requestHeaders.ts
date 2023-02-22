import { RawAxiosRequestHeaders } from 'axios';
import Cookies from 'universal-cookie';
import { ACCESS_TOKEN_COOKIE_NAME } from '../../common/constants';

export const headers: RawAxiosRequestHeaders = {
  'Content-Type': 'application/json',
  'Access-Control-Allow-Origin': '*'
};

export function getAuthenticatedHeader(): RawAxiosRequestHeaders {
  const cookies = new Cookies();
  const token = cookies.get(ACCESS_TOKEN_COOKIE_NAME);
  return token ? { ...headers, Authorization: 'Bearer ' + token } : headers;
}
