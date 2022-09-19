import { motion, useAnimation } from "framer-motion";
import { useEffect, useRef } from "react";
import { Card } from "react-bootstrap";
import useSound from "use-sound";
import clickSound from "../../../../assets/sounds/click.mp3";
import {
  getFieldColor,
  getRouletteNumberSequence,
} from "../../../../common/utils/rouletteUtils/rouletteUtils";
import css from "./RouletteWheel.module.css";
import {
  calculateAnimationForPosition,
  getRandomStartPosition,
} from "./rouletteWheelUtils";

const CONTAINER_REPETITIONS = 5;

export default function RouletteWheel(props: wheelProps) {
  const [playClick] = useSound(clickSound);
  const control = useAnimation();
  const rouletteBarRef = useRef<any>();
  const defaultTransition = {
    type: "tween",
    duration: 10,
  };

  let rouletteBarWidth: number;
  let currentPosition: number;

  let initialPosition: { x: string } = { x: "" };

  useEffect(() => {
    window.addEventListener("resize", updateRouletteBarWidth);
    rouletteBarWidth = rouletteBarRef.current.clientWidth;

    currentPosition = getRandomStartPosition();
    initialPosition = calculateAnimationForPosition(
      currentPosition,
      rouletteBarWidth
    );

    control.set(initialPosition);

    return () => window.removeEventListener("resize", updateRouletteBarWidth);
  }, []);

  function updateRouletteBarWidth(): void {
    rouletteBarWidth = rouletteBarRef.current.clientWidth;
    control.set(
      calculateAnimationForPosition(currentPosition, rouletteBarWidth)
    );
    playClick();
  }

  function getRouletteTiles() {
    const tiles = [];
    for (let i = 0; i < CONTAINER_REPETITIONS; i++) {
      tiles.push(
        getRouletteNumberSequence().map((value, index) => (
          <div className={`${getFieldColor(value)}`} key={index}>
            <p>{value}</p>
          </div>
        ))
      );
    }
    return tiles;
  }

  return (
    <Card bg="light">
      <div
        className={css.rouletteBar}
        ref={rouletteBarRef}
        onClick={() => control.start({ x: 0 })}
      >
        <motion.div
          className={css.tileContainer}
          initial={false}
          animate={control}
          transition={defaultTransition}
        >
          {getRouletteTiles()}
        </motion.div>
        <span className={css.roulettePointer} />
      </div>
    </Card>
  );
}

interface wheelProps {
  spinResult: number;
}
