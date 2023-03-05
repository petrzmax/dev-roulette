import { RootState } from '../store';

export const selectRollHistory = (state: RootState) => state.roulette.rollHistory;
export const selectLastRoll = (state: RootState) => state.roulette.lastRoll;
export const selectNextRollTimeStamp = (state: RootState) => state.roulette.nextRollTimeStamp;
export const selectTileCoverageFactor = (state: RootState) => state.roulette.tileCoverageFactor;
