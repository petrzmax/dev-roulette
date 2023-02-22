import { RootState } from '../store';

export const selectIsLoggedIn = (state: RootState) => state.user.isLoggedIn;
export const selectUserBalance = (state: RootState) => state.user.balance;
export const selectBots = (state: RootState) => state.user.bots;
