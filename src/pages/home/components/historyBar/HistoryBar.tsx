import { Card } from "react-bootstrap";
import ColoredCircle from "./coloredCircle/ColoredCircle";
import css from "./HistoryBar.module.css";

export default function HistoryBar(props: historyBarProps) {
  return (
    <Card bg="light">
      <div className={css.flex}>
        {props.lastRolls.map((value, index) => (
          <ColoredCircle key={index} rolledNumber={value} />
        ))}
      </div>
    </Card>
  );
}

interface historyBarProps {
  lastRolls: number[];
}
