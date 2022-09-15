import { motion } from "framer-motion";
import { useState } from "react";
import { Card } from "react-bootstrap";
import { getFieldColor, getRouletteNumberSequence } from "./rouletteUtils";
import css from "./wheel.module.css";

export default function Wheel(props: wheelProps) {
  const [isAnimating, setAnimating] = useState(false);
  return (
    <Card bg="light">
      <div
        className={css.rouletteBar}
        onClick={() => setAnimating(!isAnimating)}
      >
        <motion.div
          className={css.tileContainer}
          animate={{
            x: isAnimating ? "-133em" : "0em",
          }}
          transition={{ type: "tween", duration: 2 }}
        >
          {getRouletteNumberSequence().map((value, index) => (
            <div className={`${getFieldColor(value)}`} key={index}>
              <p>{value}</p>
            </div>
          ))}
        </motion.div>
      </div>
    </Card>
  );
}

interface wheelProps {
  spinResult: number;
}
