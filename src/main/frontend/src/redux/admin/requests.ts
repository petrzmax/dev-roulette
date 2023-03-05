import axios from 'axios';
import { dispatchMessageUrl } from '../../common/endpoints';
import { getAuthenticatedHeader } from '../common/requestHeaders';
import { MessageDto } from './admin.model.d';

export function requestDispatchMessage(messageDto: MessageDto) {
  return axios.request({
    method: 'post',
    headers: getAuthenticatedHeader(),
    url: dispatchMessageUrl,
    data: messageDto
  });
}
