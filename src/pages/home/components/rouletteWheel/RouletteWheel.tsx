import { motion, useAnimation } from "framer-motion";
import { useEffect, useRef } from "react";
import { Card } from "react-bootstrap";
import { useSelector } from "react-redux";
import useSound from "use-sound";
import clickSound from "../../../../assets/sounds/click.mp3";
import { setTileCoverageFactor } from "../../../../redux/actions/rouletteActions";
import { RootState, useAppDispatch } from "../../../../redux/store";
import RouletteTiles from "./components/RouletteTiles";
import css from "./RouletteWheel.module.css";
import {
  calculateRollAnimation,
  rollAnimationData,
} from "./rouletteWheelUtils";

export default function RouletteWheel() {
  const dispatch = useAppDispatch();
  const [playClick] = useSound(clickSound);
  const wheelAnimationControl = useAnimation();
  const rouletteBarRef = useRef<any>();

  let rouletteBarWidth: number;

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

  useEffect(initialize, []);

  function initialize(): () => void {
    window.addEventListener("resize", updateRouletteBarWidth);
    rouletteBarWidth = rouletteBarRef.current.clientWidth;

    wheelAnimationControl.set(calculateRollAnimation(getRollAnimationData()));

    return () => window.removeEventListener("resize", updateRouletteBarWidth);
  }

  function updateRouletteBarWidth(): void {
    rouletteBarWidth = rouletteBarRef.current.clientWidth;
    wheelAnimationControl.set(calculateRollAnimation(getRollAnimationData()));
    playClick();
  }

  function getRollAnimationData(): rollAnimationData {
    return {
      roll: selectLastRoll,
      rouletteBarWidth: rouletteBarWidth,
      tileCoverageFactor: selectTileCoverageFactor,
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
          animate={wheelAnimationControl}
          transition={defaultTransition}
        >
          <RouletteTiles />
        </motion.div>
        <span className={css.roulettePointer} />
      </div>
    </Card>
  );
}
