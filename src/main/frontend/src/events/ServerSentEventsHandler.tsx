import { useEffect } from 'react';
import toast from 'react-hot-toast';
import { subscribeUrl } from '../common/endpoints';
import { setRollEventData } from '../redux/roulette/actions';
import { useAppDispatch } from '../redux/store';
import { RollEventDto } from './events.model';

export default function ServerSentEventsHandler() {
  useEffect(initializeSSE, []);

  // TODO subscribe after roulette data fetch? Do as action, after receiving userdata?
  const sse = new EventSource(subscribeUrl);
  const dispatch = useAppDispatch();

  function initializeSSE() {
    sse.addEventListener('message', function (event) {
      toast(event.data);
    });

    sse.addEventListener('success', function (event) {
      toast.success(event.data);
    });

    sse.addEventListener('roll', function (event: MessageEvent<string>) {
      const rollEventDto: RollEventDto = JSON.parse(event.data);
      dispatch(setRollEventData(rollEventDto));
    });

    // TODO - It can close stream and stall
    sse.onerror = () => sse.close();

    return () => {
      sse.close();
    };
  }

  return <></>;
}
