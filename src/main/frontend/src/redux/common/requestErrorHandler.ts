import axios from 'axios';
import toast from 'react-hot-toast';

// TODO Pass and display more meaningful errors from BE
export function handleAxiosError(error: unknown): void {
  if (axios.isAxiosError(error)) {
    toast.error(error.message);
  } else {
    // Handle non axios error
  }
}
