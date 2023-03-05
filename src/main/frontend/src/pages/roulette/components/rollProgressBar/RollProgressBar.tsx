import { motion, useAnimation } from 'framer-motion';
import { useEffect } from 'react';
import { Card } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { useTimer } from 'react-timer-hook';
import { selectNextRollTimeStamp } from '../../../../redux/roulette/selectors';
import { useAppDispatch } from '../../../../redux/store';
import { fetchUserData } from '../../../../redux/user/actions';
import { selectIsLoggedIn } from '../../../../redux/user/selectors';
import css from './rollProgressBar.module.css';

export default function RollProgressBar() {
  const dispatch = useAppDispatch();
  const animationController = useAnimation();

  const nextRollTimeStamp = useSelector(selectNextRollTimeStamp);
  const isLoggedIn = useSelector(selectIsLoggedIn);

  let expiryTimestamp: Date;

  useEffect(() => {
    expiryTimestamp = new Date(nextRollTimeStamp);
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
  }, [nextRollTimeStamp]);

  const onTimerExpire = () => {
    // TODO do also using SSE?
    if (isLoggedIn) dispatch(fetchUserData());
  };

  expiryTimestamp = new Date(nextRollTimeStamp);
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
