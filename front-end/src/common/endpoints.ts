const baseURL = process.env.REACT_APP_BE_URL;
const apiURL = baseURL + '/api';

// Roulette
export const rouletteUrl = `${apiURL}/roulette/state`;
export const rouletteBetUrl = `${apiURL}/roulette/bet`;

// Roll
export const rollHistoryUrl = `${apiURL}/roll/history`;

// Session
export const sessionUrl = `${apiURL}/session`;
