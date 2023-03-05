const baseURL = process.env.REACT_APP_BE_URL;
const apiURL = baseURL + '/api';

// SSE
export const subscribeUrl = `${apiURL}/events/subscribe`;

// Roulette
export const rouletteUrl = `${apiURL}/roulette`;
export const rouletteBetUrl = `${apiURL}/roulette/bet`;

// Bots
export const botsUrl = `${apiURL}/bots`;

// Roll
export const rollHistoryUrl = `${apiURL}/roll/history`;

// User
export const userUrl = `${apiURL}/user`;

// Admin
export const dispatchMessageUrl = `${apiURL}/admin/dispatchMessage`;
