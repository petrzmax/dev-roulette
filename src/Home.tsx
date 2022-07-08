import HistoryBar from "./roulette/HistoryBar";
import ManualBetPanel from "./roulette/ManualBetPanel";
import Wheel from "./roulette/Wheel";

export default function HomePage() {
  return (
    <>
      <Wheel spinResult={10} />
      <HistoryBar lastRolls={[2, 4, 0, 4, 5, 6, 12]} />
      <ManualBetPanel />
    </>
  );
}
