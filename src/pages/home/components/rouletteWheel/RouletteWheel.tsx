import { motion, useAnimation } from "framer-motion";
import { useEffect, useRef } from "react";
import { Card } from "react-bootstrap";
import useSound from "use-sound";
import clickSound from "../../../../assets/sounds/click.mp3";
import {
  getFieldColor,
  getNumberIndexByValue,
  getRandomRouletteNumber,
  getRouletteNumberSequence,
} from "../../../../common/utils/rouletteUtils/rouletteUtils";
import css from "./RouletteWheel.module.css";

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
    //setTransition({ type: "tween", duration: 0 });

    currentPosition = getRandomStartPosition();
    initialPosition = calculateAnimationForPosition(currentPosition);

    control.set(initialPosition);

    return () => window.removeEventListener("resize", updateRouletteBarWidth);
  }, []);

  function updateRouletteBarWidth(): void {
    rouletteBarWidth = rouletteBarRef.current.clientWidth;
    control.set(calculateAnimationForPosition(currentPosition));
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

  // Nie random, tylko ustawi się na poprzednio wyrzuconym numerku
  function getRandomStartPosition(): number {
    const number = getRandomRouletteNumber();
    console.log("Random number: " + number);
    const initialIndex = getNumberIndexByValue(number);
    return initialIndex + Math.random();
  }

  function positionToEM(position: number): string {
    const tileSize = 5;
    const offset = getRouletteNumberSequence().length;
    return `-${(offset + position) * tileSize}em`;
  }

  function calculateAnimationForPosition(position: number) {
    return {
      x: `calc(${positionToEM(position)} + ${rouletteBarWidth / 2}px)`,
    };
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
