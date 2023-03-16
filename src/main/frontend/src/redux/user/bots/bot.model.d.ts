import { BotStatus } from '../../../../src/common/constants';

export interface BotDto {
  id: number;
  name: string;
  scriptBody: string;
  balance: number;
  status: BotStatus;
  errorMessage: string;
}

export interface BotCreationDto {
  name: string;
}

export interface BotPatchDto {
  id: number;
  scriptBody: string;
}
