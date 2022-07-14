import { Card } from "react-bootstrap";
import css from "./HistoryBar.module.css";
import RollColoredCircle from "./RollColoredCircle";

export default function HistoryBar(props: historyBarProps) {
  return (
    <Card bg="light">
      <div className={css.flex}>
        {props.lastRolls.map((value, index) => (
          <RollColoredCircle key={index} rolledNumber={value} />
        ))}
      </div>
    </Card>
  );
}

interface historyBarProps {
  lastRolls: number[];
}
