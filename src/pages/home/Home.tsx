import { useEffect, useState } from "react";
import { Col, Container, Row, Spinner } from "react-bootstrap";
import { useSelector } from "react-redux";
import { BetType } from "../../common/constants";
import { isEmpty } from "../../common/utils/rouletteUtils/typeScriptUtils";
import { fetchRouletteState } from "../../redux/actions/rouletteActions";
import { fetchSession } from "../../redux/actions/sessionActions";
import { RootState, useAppDispatch } from "../../redux/store";
import BetAmountPanel from "./components/betAmountPanel/BetAmountPanel";
import HistoryBar from "./components/historyBar/HistoryBar";
import ManualBetPanel from "./components/manualBetPanel/ManualBetPanel";
import RollProgressBar from "./components/rollProgressBar/RollProgressBar";
import RouletteWheel from "./components/rouletteWheel/RouletteWheel";
import { BetContext } from "./context/BetContext";

export default function Home() {
  const dispatch = useAppDispatch();
  const [betAmount, setBetAmount] = useState(0);

  useEffect(() => {
    dispatch(fetchSession());
    dispatch(fetchRouletteState());
  }, []);

  const selectRollHistory = useSelector(
    (state: RootState) => state.roulette.rollHistory
  );

  return isEmpty(selectRollHistory) ? (
    // TODO: Wyśrodkować
    <Spinner animation={"border"} />
  ) : (
    <>
      <RollProgressBar />
      <RouletteWheel />
      <HistoryBar />
      <BetContext.Provider value={{ betAmount, setBetAmount }}>
        <BetAmountPanel />
        <Container className="g-0">
          <Row className="g-3">
            <Col>
              <ManualBetPanel text="Red" betType={BetType.RED} />
            </Col>
            <Col>
              <ManualBetPanel text="0" betType={BetType.GREEN} />
            </Col>
            <Col>
              <ManualBetPanel text="Black" betType={BetType.BLACK} />
            </Col>
          </Row>
        </Container>
      </BetContext.Provider>
    </>
  );
}
