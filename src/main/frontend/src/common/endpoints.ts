const baseURL = process.env.REACT_APP_BE_URL;
const apiURL = baseURL + '/api';

// Roulette
export const rouletteUrl = `${apiURL}/roulette`;
export const rouletteBetUrl = `${apiURL}/roulette/bet`;

// Bots
export const botsUrl = `${apiURL}/bots`;

// Roll
export const rollHistoryUrl = `${apiURL}/roll/history`;

// Session
export const sessionUrl = `${apiURL}/session`;
