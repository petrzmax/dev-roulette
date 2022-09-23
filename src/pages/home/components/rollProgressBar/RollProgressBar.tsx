import { useEffect, useState } from "react";
import { Card, ProgressBar } from "react-bootstrap";
import css from "./rollProgressBar.module.css";

export default function RollProgressBar() {
  const [value, setValue] = useState(0);
  const [timeRemaining, setTimeRemaining] = useState(30);
  const MAX: number = 30;
  let timer: number = 0;

  /// to się może przydać https://www.npmjs.com/package/react-timer-hook

  useEffect(() => {
    const interval = setInterval(() => {
      setValue((oldValue) => {
        const newValue = oldValue + 1;
        if (newValue === 100) {
          clearInterval(interval);
        }
        return newValue;
      });
    }, 1000);
  }, []);

  return (
    <Card bg="light">
      <ProgressBar now={value} max={MAX} />
      <div>
        <progress
          className={css.rollProgressBar}
          value={value}
          max={MAX}
        ></progress>
      </div>
    </Card>
  );
}
