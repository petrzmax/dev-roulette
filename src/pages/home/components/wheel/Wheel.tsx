import { motion, useAnimation } from "framer-motion";
import { useEffect, useRef } from "react";
import { Card } from "react-bootstrap";
import {
  getFieldColor,
  getNumberIndexByValue,
  getRandomRouletteNumber,
  getRouletteNumberSequence,
} from "../../../../common/rouletteUtils/rouletteUtils";
import css from "./Wheel.module.css";

const CONTAINER_REPETITIONS = 5;

export default function Wheel(props: wheelProps) {
  const control = useAnimation();
  const rouletteBarRef = useRef<any>();
  let rouletteBarWidth: number;

  let initialPosition: { x: string } = { x: "" };

  useEffect(() => {
    window.addEventListener("resize", updateRouletteBarWidth);
    updateRouletteBarWidth();
    initialPosition = {
      x: `calc(${positionToEM(getRandomStartPosition())} + ${
        rouletteBarWidth / 2
      }px)`,
    };
    control.start(initialPosition);

    return () => window.removeEventListener("resize", updateRouletteBarWidth);
  }, []);

  const transition = {
    type: "tween",
    duration: 2,
  };

  function updateRouletteBarWidth(): void {
    rouletteBarWidth = rouletteBarRef.current.clientWidth;
    control.start({
      x: `calc(${initialPosition} + ${rouletteBarWidth / 2}px)`,
    });
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

  return (
    <Card bg="light">
      <div
        className={css.rouletteBar}
        ref={rouletteBarRef}
        onClick={() => control.start({ x: "-300em" })}
      >
        <motion.div
          className={css.tileContainer}
          animate={control}
          transition={transition}
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
