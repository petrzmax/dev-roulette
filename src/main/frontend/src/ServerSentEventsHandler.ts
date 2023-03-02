import toast from 'react-hot-toast';
import { subscribeUrl } from './common/endpoints';

export function initializeSSE(): () => void {
  // TODO subscribe after roulette data fetch?
  const sse = new EventSource(subscribeUrl);
  sse.addEventListener('test', function (event) {
    toast(event.data);
  });

  sse.onmessage = (e) => toast(e.data);

  // TODO - It can close stream and stall
  sse.onerror = () => sse.close();

  return () => {
    sse.close();
  };
}
