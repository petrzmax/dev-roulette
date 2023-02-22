export interface RouletteDto {
  rollHistory: number[];
  tileCoverageFactor: number;
  nextRollTimeStamp: string;
}

export interface BetDto {
  betType: BetType;
  amount: number;
}
