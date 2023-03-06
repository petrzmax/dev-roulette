import { motion, useAnimation } from 'framer-motion';
import { useEffect } from 'react';
import { Card } from 'react-bootstrap';
import toast from 'react-hot-toast';
import { useSelector } from 'react-redux';
import { useTimer } from 'react-timer-hook';
import { selectNextRollTimeStamp } from '../../../../redux/roulette/selectors';
import { useAppDispatch } from '../../../../redux/store';
import css from './rollProgressBar.module.css';

export default function RollProgressBar() {
  const dispatch = useAppDispatch();
  const animationController = useAnimation();

  const nextRollTimeStamp = useSelector(selectNextRollTimeStamp);

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
    toast.success('Time to roll!');
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
