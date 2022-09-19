import {
  getNumberIndexByValue,
  getRandomRouletteNumber,
  getRouletteNumberSequence,
} from "../../../../common/utils/rouletteUtils/rouletteUtils";

export function getRandomStartPosition(): number {
  const number = getRandomRouletteNumber();
  console.log("Random number: " + number);
  const initialIndex = getNumberIndexByValue(number);
  return initialIndex + Math.random();
}

export function calculateAnimationForPosition(
  position: number,
  rouletteBarWidth: number
) {
  return {
    x: `calc(${positionToEM(position)} + ${rouletteBarWidth / 2}px)`,
  };
}

function positionToEM(position: number): string {
  const tileSize = 5;
  const offset = getRouletteNumberSequence().length;
  return `-${(offset + position) * tileSize}em`;
}
