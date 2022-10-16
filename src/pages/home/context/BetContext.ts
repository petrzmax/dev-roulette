import { createContext } from "react";

export const BetContext = createContext<{
  betAmount: number;
  setBetAmount: (value: number) => void;
}>({ betAmount: 0, setBetAmount: () => undefined });
