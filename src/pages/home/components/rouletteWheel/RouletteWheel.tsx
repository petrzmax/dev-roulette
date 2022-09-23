import { motion, useAnimation } from "framer-motion";
import { useEffect, useRef } from "react";
import { Card } from "react-bootstrap";
import { useSelector } from "react-redux";
import useSound from "use-sound";
import clickSound from "../../../../assets/sounds/click.mp3";
import {
  getFieldColor,
  getPositionByRollValue,
  getRouletteNumberSequence,
} from "../../../../common/utils/rouletteUtils/rouletteUtils";
import { setTileCoverageFactor } from "../../../../redux/actions/rouletteActions";
import { RootState, useAppDispatch } from "../../../../redux/store";
import css from "./RouletteWheel.module.css";
import { positionToEM } from "./rouletteWheelUtils";

const CONTAINER_REPETITIONS = 5;

export default function RouletteWheel() {
  const [playClick] = useSound(clickSound);
  const control = useAnimation();
  const rouletteBarRef = useRef<any>();
  const isInitialPositionSet: boolean = false;
  const defaultTransition = {
    type: "tween",
    duration: 10,
  };

  const selectLastRoll = useSelector(
    (state: RootState) =>
      state.roulette.rollHistory[state.roulette.rollHistory.length - 1]
  );

  const selectTileCoverageFactor = useSelector(
    (state: RootState) => state.roulette.tileCoverageFactor
  );

  const dispatch = useAppDispatch();

  let rouletteBarWidth: number;
  let currentPosition: number;

  let initialPosition: { x: string } = { x: "" };

  useEffect(() => {
    window.addEventListener("resize", updateRouletteBarWidth);
    rouletteBarWidth = rouletteBarRef.current.clientWidth;
    dispatch(setTileCoverageFactor(Math.random()));

    initialPosition = calculateAnimationForRoll(
      getPositionByRollValue(selectLastRoll)
    );

    control.set(initialPosition);

    return () => window.removeEventListener("resize", updateRouletteBarWidth);
  }, []);

  useEffect(() => {
    if (isInitialPositionSet) {
      control.start(calculateAnimationForRoll(selectLastRoll));
    } else {
      control.set(calculateAnimationForRoll(selectLastRoll));
    }
  }, [selectLastRoll]);

  function updateRouletteBarWidth(): void {
    rouletteBarWidth = rouletteBarRef.current.clientWidth;
    control.set(calculateAnimationForRoll(currentPosition));
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

  function calculateAnimationForRoll(roll: number) {
    return {
      x: `calc(${positionToEM(
        getPositionByRollValue(roll) + selectTileCoverageFactor
      )} + ${rouletteBarWidth / 2}px)`,
    };
  }

  return (
    <Card bg="light">
      <div
        className={css.rouletteBar}
        ref={rouletteBarRef}
        onClick={() => dispatch(setTileCoverageFactor(Math.random()))}
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
