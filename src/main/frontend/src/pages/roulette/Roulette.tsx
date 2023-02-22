import { useState } from 'react';
import { Col, Container, Row, Spinner } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { BetType } from '../../common/constants';
import { isEmpty } from '../../common/utils/rouletteUtils/typeScriptUtils';
import { selectRollHistory } from '../../redux/roulette/selectors';
import BetAmountPanel from './components/betAmountPanel/BetAmountPanel';
import HistoryBar from './components/historyBar/HistoryBar';
import ManualBetPanel from './components/manualBetPanel/ManualBetPanel';
import RollProgressBar from './components/rollProgressBar/RollProgressBar';
import Wheel from './components/wheel/Wheel';
import { BetContext } from './context/BetContext';

export default function Roulette() {
  const [betAmount, setBetAmount] = useState(0);

  const rollHistory = useSelector(selectRollHistory);

  return isEmpty(rollHistory) ? (
    // TODO: Wyśrodkować
    <Spinner animation={'border'} />
  ) : (
    <>
      <RollProgressBar />
      <Wheel />
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
