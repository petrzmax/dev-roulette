export const RouletteActionTypes = {
  FETCH_ROULETTE_STATE: '[Roulette page] Fetch roulette state',
  SET_ROULETTE_STATE: '[Roulette page] Set roulette state',
  POST_ROULETTE_BET: '[Roulette page] Post roulette bet'
};

export const BotsActionTypes = {
  FETCH_BOTS: '[Bots page] Fetch bots',
  SET_BOTS: '[Bots page] Set bots'
};

export const SessionActionTypes = {
  FETCH_SESSION: '[Login card] Fetch session state',
  SET_SESSION: '[Login card] Set session state',
  CLEAR_SESSION: '[Login card] Clear session state',
  SET_IS_USER_LOGGED_IN: '[Login card] Set "isLoggedIn" state value',
  REDUCE_BALANCE: '[Roulette page] Reduce session balance',
  SET_ACCESS_TOKEN_IN_COOKIE: '[Login card] Set access token in a cookie',
  LOGIN: '[Menu bar] Login user',
  LOGOUT: '[Menu bar] Logout user'
};
