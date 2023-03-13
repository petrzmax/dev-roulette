import axios from 'axios';
import { botsUrl } from '../../../common/endpoints';
import { getAuthenticatedHeader } from '../../common/requestHeaders';
import { BotCreationDto, BotPatchDto } from './bot.model.d';

export function requestCreateBot(botDto: BotCreationDto) {
  return axios.request({
    method: 'post',
    headers: getAuthenticatedHeader(),
    url: botsUrl,
    data: botDto
  });
}

export function requestDeleteBot(botId: number) {
  return axios.request({
    method: 'delete',
    headers: getAuthenticatedHeader(),
    url: botsUrl + '/' + botId
  });
}

export function requestPatchBotScript(botDto: BotPatchDto) {
  return axios.request({
    method: 'patch',
    headers: getAuthenticatedHeader(),
    url: botsUrl + '/' + botDto.id,
    data: botDto
  });
}
