import { TILE_SIZE } from '../../../../common/constants';
import {
  getPositionByRoll,
  getRouletteNumberSequence
} from '../../../../common/utils/rouletteUtils/rouletteUtils';

export function calculateAnimation(animationData: rollAnimationData) {
  return {
    x: `calc(${transformRollToEM(animationData)} + ${animationData.rouletteBarWidth / 2}px)`
  };
}

export function transformRollToEM(transformData: transformRollData): string {
  // TODO
  const offset = transformData.offset ? transformData.offset : getRouletteNumberSequence().length;

  const positionWithTileCoverage =
    getPositionByRoll(transformData.roll) + transformData.tileCoverageFactor;

  return `-${(offset + positionWithTileCoverage) * TILE_SIZE}em`;
}

export interface rollAnimationData extends transformRollData {
  rouletteBarWidth: number;
}

interface transformRollData {
  offset?: number;
  roll: number;
  tileCoverageFactor: number;
}
