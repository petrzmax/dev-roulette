export const RouletteActionTypes = {
  FETCH_ROULETTE_STATE: '[Roulette page] Fetch roulette state',
  SET_ROULETTE_STATE: '[Roulette page] Set roulette state',
  POST_ROULETTE_BET: '[Roulette page] Post roulette bet'
};

export const BotsActionTypes = {
  FETCH_BOTS: '[Bots page] Fetch bots',
  SET_BOTS: '[Bots page] Set bots',
  CLEAR_BOTS: '[Login card] Clear bots state'
};

export const UserActionTypes = {
  FETCH_USER_DATA: '[Login card] Fetch user data',
  SET_USER_DATA: '[Login card] Set user data',
  CLEAR_USER_DATA: '[Login card] Clear user data',
  SET_IS_USER_LOGGED_IN: '[Login card] Set "isLoggedIn" state value',
  REDUCE_BALANCE: '[Roulette page] Reduce user balance',
  SET_ACCESS_TOKEN_IN_COOKIE: '[Login card] Set access token in a cookie',
  LOGIN: '[Menu bar] Login user',
  LOGOUT: '[Menu bar] Logout user'
};
