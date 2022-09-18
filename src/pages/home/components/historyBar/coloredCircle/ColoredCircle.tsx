import { getFieldColor } from "../../../../../common/utils/rouletteUtils/rouletteUtils";
import css from "./ColoredCircle.module.css";

export default function ColoredCircle(props: rollColoredCircleProps) {
  return (
    <div className={`${css.circle} ${getFieldColor(props.rolledNumber)}`}>
      <p>{props.rolledNumber}</p>
    </div>
  );
}

export interface rollColoredCircleProps {
  rolledNumber: number;
}
