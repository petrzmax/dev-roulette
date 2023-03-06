import { NUMBER_OUT_OF_ROULETTE_RANGE } from '../../messages';
import css from './rouletteUtils.module.css';

const ROULETTE_NUMBER_SEQUENCE: ReadonlyArray<number> = [
  0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14,
  31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26
];

export function getRouletteNumberSequence(): ReadonlyArray<number> {
  return ROULETTE_NUMBER_SEQUENCE;
}

export function getPositionByRoll(value: number) {
  return ROULETTE_NUMBER_SEQUENCE.findIndex((x) => x === value);
}

// In number ranges from 1 to 10 and 19 to 28, odd numbers are red and even are black.
// In ranges from 11 to 18 and 29 to 36, odd numbers are black and even are red.
export function getFieldColor(value: number): string {
  let result: string;

  if (value === 0) {
    result = css.green;
  } else if ((value >= 1 && value <= 10) || (value >= 19 && value <= 28)) {
    result = isEven(value) ? css.black : css.red;
  } else if ((value >= 11 && value <= 18) || (value >= 29 && value <= 36)) {
    result = isEven(value) ? css.red : css.black;
  } else {
    throw new Error(NUMBER_OUT_OF_ROULETTE_RANGE);
  }

  return result;
}

function isEven(value: number): boolean {
  return value % 2 === 0;
}
