import {
  getPositionByRollValue,
  getRouletteNumberSequence,
} from "../../../../common/utils/rouletteUtils/rouletteUtils";


export function positionToEM(position: number): string {
  const tileSize = 5; // TODO - move to constant, make it available to css
  const offset = getRouletteNumberSequence().length;
  return `-${(offset + position) * tileSize}em`;
}
