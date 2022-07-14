import HistoryBar from "../roulette/HistoryBar";
import ManualBetPanel from "../roulette/ManualBetPanel";
import RollProgressBar from "../roulette/RollProgressBar";
import Wheel from "../roulette/Wheel";

export default function Home() {
  return (
    <>
      <RollProgressBar />
      <Wheel spinResult={10} />
      <HistoryBar lastRolls={[2, 4, 0, 4, 5, 6, 12]} />
      <ManualBetPanel />
    </>
  );
}
