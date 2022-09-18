import { Col, Container, Row } from "react-bootstrap";
import BetAmountPanel from "./components/betAmountPanel/BetAmountPanel";
import HistoryBar from "./components/historyBar/HistoryBar";
import ManualBetPanel from "./components/manualBetPanel/ManualBetPanel";
import Wheel from "./components/wheel/Wheel";

export default function Home() {
  return (
    <>
      {/* <RollProgressBar /> */}
      <Wheel spinResult={10} />
      <HistoryBar lastRolls={[2, 4, 0, 4, 5, 6, 12]} />
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
