import axios, { AxiosResponse } from "axios";
import { useEffect } from "react";
import { Col, Container, Row, Spinner } from "react-bootstrap";
import { useSelector } from "react-redux";
import { urlRoulette } from "../../common/endpoints";
import { isEmpty } from "../../common/utils/rouletteUtils/typeScriptUtils";
import { setRouletteState } from "../../redux/actions/rouletteActions";
import { rouletteDto } from "../../redux/reducers/rouletteReducer";
import { RootState, useAppDispatch } from "../../redux/store";
import BetAmountPanel from "./components/betAmountPanel/BetAmountPanel";
import HistoryBar from "./components/historyBar/HistoryBar";
import ManualBetPanel from "./components/manualBetPanel/ManualBetPanel";
import RouletteWheel from "./components/rouletteWheel/RouletteWheel";

export default function Home() {
  const dispatch = useAppDispatch();

  useEffect(() => {
    fetchRouletteState();
  }, []);

  function fetchRouletteState(): void {
    axios.get(urlRoulette).then((response: AxiosResponse<rouletteDto>) => {
      dispatch(setRouletteState(response.data));
    });
  }

  const selectRollHistory = useSelector(
    (state: RootState) => state.roulette.rollHistory
  );

  return isEmpty(selectRollHistory) ? (
    // TODO: Wyśrodkować
    <Spinner animation={"border"} />
  ) : (
    <>
      {/* <RollProgressBar /> */}
      <RouletteWheel />
      <HistoryBar />
      <BetAmountPanel />
      <Container className="g-0">
        <Row className="g-3">
          <Col>
            <ManualBetPanel text="Red" color="red" />
          </Col>
          <Col>
            <ManualBetPanel text="0" color="red" />
          </Col>
          <Col>
            <ManualBetPanel text="Black" color="red" />
          </Col>
        </Row>
      </Container>
    </>
  );
}
