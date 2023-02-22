import axios from 'axios';

// TODO Display error in meaningful way
export function handleAxiosError(error: unknown): void {
  if (axios.isAxiosError(error)) {
    console.log(error.response);
    return;
  }
  console.log(error);
}
