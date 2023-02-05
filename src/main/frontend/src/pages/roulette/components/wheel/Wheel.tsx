import { motion, ResolvedValues, Transition, useAnimation } from 'framer-motion';
import { useEffect, useRef, useState } from 'react';
import { Card } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import useSound from 'use-sound';
import clickSound from '../../../../assets/sounds/click.mp3';
import {
  selectLastRoll,
  selectTileCoverageFactor
} from '../../../../redux/reducers/rouletteReducer';
import { useAppDispatch } from '../../../../redux/store';
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

  const lastRoll = useSelector(selectLastRoll);
  const tileCoverageFactor = useSelector(selectTileCoverageFactor);

  const defaultTransition: Transition = {
    type: 'spring',
    stiffness: 100,
    damping: 50,
    mass: 1,
    velocity: 2,
    restSpeed: 0.04,
    restDelta: 0.04
  };

  useEffect(() => {
    if (tempBool) {
      animationController.start(calculateAnimation(getAnimationData(74)));
    }
  }, [lastRoll]);

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
      roll: lastRoll,
      rouletteBarWidth: rouletteBarRef.current!.clientWidth,
      tileCoverageFactor: tileCoverageFactor
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