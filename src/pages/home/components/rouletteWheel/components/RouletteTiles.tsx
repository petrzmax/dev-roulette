import { CONTAINER_REPETITIONS } from '../../../../../common/constants';
import {
  getFieldColor,
  getRouletteNumberSequence
} from '../../../../../common/utils/rouletteUtils/rouletteUtils';
import { repeatArray } from '../../../../../common/utils/rouletteUtils/typeScriptUtils';

export default function RouletteTiles() {
  return (
    <>
      {repeatArray(getRouletteNumberSequence(), CONTAINER_REPETITIONS).map((value, index) => (
        <div className={`${getFieldColor(value)}`} key={index}>
          <p>{value}</p>
        </div>
      ))}
    </>
  );
}
