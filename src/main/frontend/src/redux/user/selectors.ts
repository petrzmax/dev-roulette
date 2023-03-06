import { RootState } from '../store';

export const selectIsLoggedIn = (state: RootState) => state.user.isLoggedIn;
export const selectRole = (state: RootState) => state.user.role;
export const selectBalance = (state: RootState) => state.user.balance;
export const selectBots = (state: RootState) => state.user.bots;
