import { motion, useAnimation } from 'framer-motion';
import { useEffect } from 'react';
import { Card } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { useTimer } from 'react-timer-hook';
import { fetchRouletteState } from '../../../../redux/actions/rouletteActions';
import { fetchSession } from '../../../../redux/actions/sessionActions';
import { RootState, useAppDispatch } from '../../../../redux/store';
import css from './rollProgressBar.module.css';

export default function RollProgressBar() {
  const dispatch = useAppDispatch();
  const animationController = useAnimation();

  const selectNextRollTimeStamp = useSelector(
    (state: RootState) => state.roulette.nextRollTimeStamp
  );

  const selectIsLoggedIn = useSelector((state: RootState) => state.session.isLoggedIn);

  let expiryTimestamp: Date;

  useEffect(() => {
    expiryTimestamp = new Date(selectNextRollTimeStamp);
    restart(expiryTimestamp);
    animationController.set({ width: '0%' });
    animationController.start(
      { width: '100%' },
      {
        type: 'tween',
        ease: 'linear',
        duration: Math.abs(expiryTimestamp.getSeconds() - new Date().getSeconds())
      }
    );
  }, [selectNextRollTimeStamp]);

  const onTimerExpire = () => {
    dispatch(fetchRouletteState());

    if (selectIsLoggedIn) dispatch(fetchSession());
  };

  expiryTimestamp = new Date(selectNextRollTimeStamp);
  const { seconds, isRunning, start, pause, resume, restart } = useTimer({
    expiryTimestamp,
    onExpire: onTimerExpire
  });

  return (
    <Card bg="light">
      <div>
        <motion.div className={css.progressBar} animate={animationController} />
        <p className={css.message}>Rolling in {seconds}...</p>
      </div>
    </Card>
  );
}
