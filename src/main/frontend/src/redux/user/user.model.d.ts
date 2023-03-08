export interface UserDataDto {
  role: string;
  balance: number;
  bots: BotDto[];
}

export interface BotDto {
  id: number;
  name: string;
  scriptBody: string;
  balance: number;
  enabled: boolean;
  errorMessage: string;
}

export interface BotCreationDto {
  name: string;
}
