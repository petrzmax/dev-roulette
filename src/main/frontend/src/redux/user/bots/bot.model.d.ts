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

export interface BotPatchDto {
  id: number;
  scriptBody: string;
}
