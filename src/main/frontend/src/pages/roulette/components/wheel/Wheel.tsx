import { motion, ResolvedValues, Transition, useAnimation } from 'framer-motion';
import { useEffect, useRef, useState } from 'react';
import { Card } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { pushLastRollHistory } from '../../../../redux/roulette/actions';
import { selectLastRoll, selectTileCoverageFactor } from '../../../../redux/roulette/selectors';
import { useAppDispatch } from '../../../../redux/store';
import { fetchUserData } from '../../../../redux/user/actions';
import { selectIsLoggedIn } from '../../../../redux/user/selectors';
import Tiles from './components/Tiles';
import css from './Wheel.module.css';
import { calculateAnimation, rollAnimationData } from './WheelUtils';

export default function Wheel() {
  const dispatch = useAppDispatch();
  // const [playClick] = useSound(clickSound);
  const animationController = useAnimation();
  const rouletteBarRef = useRef<HTMLDivElement>(null);
  const tileContainerRef = useRef<HTMLDivElement>(null);
  const [tempBool, setTempBool] = useState(false);

  const isLoggedIn = useSelector(selectIsLoggedIn);
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
    // const styles = getComputedStyle(tileContainerRef.current!);
    // console.log(styles.transform);
  }

  function getAnimationData(offset?: number): rollAnimationData {
    return {
      offset: offset,
      roll: lastRoll,
      // TODO
      // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
      rouletteBarWidth: rouletteBarRef.current!.clientWidth,
      tileCoverageFactor: tileCoverageFactor
    };
  }
  // TODO
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  function onUpdate(latest: ResolvedValues) {
    //Todo use to play sound
    //playClick();
    //console.log(latest.x);
  }

  function onComplete() {
    // TODO do also using SSE?
    if (isLoggedIn) dispatch(fetchUserData());
    // TODO Update status bar - No instant roll countdown
    dispatch(pushLastRollHistory());
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
