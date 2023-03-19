import axios from 'axios';
import { botsUrl } from '../../../common/endpoints';
import { getAuthenticatedHeader } from '../../common/requestHeaders';
import { getBotScriptUrl, getBotStatusUrl, getBotUrl } from './../../../common/endpoints';
import { BotCreationDto, BotScriptDto, BotStatusDto } from './bot.model.d';

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
    url: getBotUrl(botId)
  });
}

export function requestUpdateBotScript(botDto: BotScriptDto) {
  const { scriptBody } = botDto;

  return axios.request({
    method: 'patch',
    headers: getAuthenticatedHeader(),
    url: getBotScriptUrl(botDto.id),
    data: { scriptBody }
  });
}

export function requestUpdateBotStatus(botDto: BotStatusDto) {
  const { status } = botDto;

  return axios.request({
    method: 'patch',
    headers: getAuthenticatedHeader(),
    url: getBotStatusUrl(botDto.id),
    data: { status }
  });
}
