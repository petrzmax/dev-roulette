import { motion, ResolvedValues, Transition, useAnimation } from 'framer-motion';
import { useEffect, useRef, useState } from 'react';
import { Card } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import useSound from 'use-sound';
import clickSound from '../../../../assets/sounds/click.mp3';
import { RootState, useAppDispatch } from '../../../../redux/store';
import Tiles from './components/Tiles';
import css from './Wheel.module.css';
import { calculateAnimation, rollAnimationData } from './WheelUtils';

export default function Wheel() {
  const dispatch = useAppDispatch();
  const [playClick] = useSound(clickSound);
  const animationController = useAnimation();
  const rouletteBarRef = useRef<HTMLDivElement>(null);
  const tileContainerRef = useRef<HTMLDivElement>(null);
  const [tempBool, setTempBool] = useState(false);

  const defaultTransition: Transition = {
    type: 'spring',
    stiffness: 100,
    damping: 50,
    mass: 1,
    velocity: 2,
    restSpeed: 0.05,
    restDelta: 0.05
  };

  const selectLastRoll = useSelector(
    (state: RootState) => state.roulette.rollHistory[state.roulette.rollHistory.length - 1]
  );

  const selectTileCoverageFactor = useSelector(
    (state: RootState) => state.roulette.tileCoverageFactor
  );

  useEffect(() => {
    if (tempBool) {
      animationController.start(calculateAnimation(getAnimationData(74)));
    }
  }, [selectLastRoll]);

  useEffect(() => {
    updateRoulettePosition();
    setTempBool(true);
  }, []);

  function updateRoulettePosition(): void {
    animationController.set(calculateAnimation(getAnimationData()));
    console.log(getAnimationData());
    const styles = getComputedStyle(tileContainerRef.current!);
    console.log(styles.transform);
  }

  function getAnimationData(offset?: number): rollAnimationData {
    return {
      offset: offset,
      roll: selectLastRoll,
      rouletteBarWidth: rouletteBarRef.current!.clientWidth,
      tileCoverageFactor: selectTileCoverageFactor
    };
  }

  function onUpdate(latest: ResolvedValues) {
    //Todo use to play sound
    //playClick();
    //console.log(latest.x);
  }

  function onComplete() {
    console.log('Animation completed');
  }

  return (
    <Card bg="light" onClick={updateRoulettePosition}>
      <div className={css.rouletteBar} ref={rouletteBarRef}>
        <motion.div
          ref={tileContainerRef}
          onUpdate={onUpdate}
          onAnimationComplete={onComplete}
          className={css.tileContainer}
          animate={animationController}
          transition={defaultTransition}
        >
          <Tiles />
        </motion.div>
        <span className={css.roulettePointer} />
      </div>
    </Card>
  );
}
