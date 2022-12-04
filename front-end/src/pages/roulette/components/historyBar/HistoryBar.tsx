import { Card } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { RootState } from '../../../../redux/store';
import ColoredCircle from './coloredCircle/ColoredCircle';
import css from './HistoryBar.module.css';

export default function HistoryBar() {
  const selectRollHistory = useSelector((state: RootState) => state.roulette.rollHistory);

  return (
    <Card bg="light">
      <div className={css.flex}>{mapHistory()}</div>
    </Card>
  );

  function mapHistory() {
    return selectRollHistory.map((value, index) => (
      <ColoredCircle key={index} rolledNumber={value} />
    ));
  }
}
