import { Card } from "react-bootstrap";
import css from "./HistoryBar.module.css";
import { getFieldColor } from "./rouletteUtils";

export default function HistoryBar(props: historyBarProps) {
  return (
    <Card bg="light">
      <div className={css.flex}>
        {props.lastRolls.map((value, index) => (
          <div
            className={`${css.circle} my-2 ${getFieldColor(value)}`}
            key={index}
          >
            <p className={css.text}>{value}</p>
          </div>
        ))}
      </div>
    </Card>
  );
}

interface historyBarProps {
  lastRolls: number[];
}
