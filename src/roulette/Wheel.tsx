import { Card } from "react-bootstrap";
import { getFieldColor, getRouletteNumberSequence } from "./rouletteUtils";
import css from "./wheel.module.css";

export default function Wheel(props: wheelProps) {
  return (
    <Card bg="light">
      <div className={css.bar}>
        {getRouletteNumberSequence().map((value, index) => (
          <div className={`${css.tile} ${getFieldColor(value)}`} key={index}>
            <p>{value}</p>
          </div>
        ))}
      </div>
    </Card>
  );
}

interface wheelProps {
  spinResult: number;
}
