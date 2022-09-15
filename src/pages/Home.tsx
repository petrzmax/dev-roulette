import { Col, Container, Row } from "react-bootstrap";
import HistoryBar from "../roulette/HistoryBar";
import AmountPanel from "../roulette/manualBet/AmountPanel";
import ColorBetSelectorPanel from "../roulette/manualBet/ColorBetSelectorPanel";
import Wheel from "../roulette/Wheel";

export default function Home() {
  return (
    <>
      {/* <RollProgressBar /> */}
      <Wheel spinResult={10} />
      <HistoryBar lastRolls={[2, 4, 0, 4, 5, 6, 12]} />
      <AmountPanel />
      <Container className="g-0">
        <Row className="g-3">
          <Col>
            <ColorBetSelectorPanel text="Red" color="red" />
          </Col>
          <Col>
            <ColorBetSelectorPanel text="0" color="red" />
          </Col>
          <Col>
            <ColorBetSelectorPanel text="Black" color="red" />
          </Col>
        </Row>
      </Container>
    </>
  );
}
