export const CONTAINER_REPETITIONS = 5;
export const TILE_SIZE = 5; // (em) TODO - make it available for css

export const ACCESS_TOKEN_COOKIE_NAME = 'access_token';

export enum BetType {
  RED,
  BLACK,
  GREEN,
  EVEN,
  ODD,
  CUSTOM
}

// TODO should be in bot.model.d.ts but for some reason it is not properly resolved
export enum BotStatus {
  READY = 'READY',
  RUNNING = 'RUNNING',
  FAILED = 'FAILED'
}
