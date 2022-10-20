import { motion, Transition, useAnimation } from 'framer-motion';
import { useEffect, useRef } from 'react';
import { Card } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import useSound from 'use-sound';
import clickSound from '../../../../assets/sounds/click.mp3';
import { RootState, useAppDispatch } from '../../../../redux/store';
import RouletteTiles from './components/RouletteTiles';
import css from './RouletteWheel.module.css';
import { calculateAnimation, rollAnimationData } from './rouletteWheelUtils';

export default function RouletteWheel() {
  const dispatch = useAppDispatch();
  const [playClick] = useSound(clickSound);
  const animationController = useAnimation();
  const rouletteBarRef = useRef<any>();

  let rouletteBarWidth: number;

  const defaultTransition: Transition = {
    type: 'spring',
    stiffness: 100,
    damping: 50,
    mass: 1,
    velocity: 2,
    restSpeed: 0.01,
    restDelta: 0.01
  };

  const selectLastRoll = useSelector(
    (state: RootState) => state.roulette.rollHistory[state.roulette.rollHistory.length - 1]
  );

  const selectTileCoverageFactor = useSelector(
    (state: RootState) => state.roulette.tileCoverageFactor
  );

  useEffect(initialize, []);

  function initialize(): () => void {
    window.addEventListener('resize', updateRouletteBarWidth);
    rouletteBarWidth = rouletteBarRef.current.clientWidth;
    animationController.set(calculateAnimation(getAnimationData()));

    return () => window.removeEventListener('resize', updateRouletteBarWidth);
  }

  function updateRouletteBarWidth(): void {
    rouletteBarWidth = rouletteBarRef.current.clientWidth;
    animationController.set(calculateAnimation(getAnimationData()));
    playClick();
  }

  function getAnimationData(): rollAnimationData {
    return {
      roll: selectLastRoll,
      rouletteBarWidth: rouletteBarWidth,
      tileCoverageFactor: selectTileCoverageFactor
    };
  }

  return (
    <Card bg="light">
      <div className={css.rouletteBar} ref={rouletteBarRef}>
        <motion.div
          className={css.tileContainer}
          animate={animationController}
          transition={defaultTransition}
        >
          <RouletteTiles />
        </motion.div>
        <span className={css.roulettePointer} />
      </div>
    </Card>
  );
}
