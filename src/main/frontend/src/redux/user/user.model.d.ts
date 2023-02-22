export interface UserDataDto {
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
