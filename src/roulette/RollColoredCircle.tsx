import css from "./rollColoredCircle.module.css";
import { getFieldColor } from "./rouletteUtils";

export default function RollColoredCircle(props: rollColoredCircleProps) {
  return (
    <div className={`${css.circle} ${getFieldColor(props.rolledNumber)}`}>
      <p className={css.text}>{props.rolledNumber}</p>
    </div>
  );
}

export interface rollColoredCircleProps {
  rolledNumber: number;
}
