import { Card } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { selectRollHistory } from '../../../../redux/roulette/selectors';
import ColoredCircle from './coloredCircle/ColoredCircle';
import css from './HistoryBar.module.css';

export default function HistoryBar() {
  const rollHistory = useSelector(selectRollHistory);

  return (
    <Card bg="light">
      <div className={css.flex}>{mapHistory()}</div>
    </Card>
  );

  function mapHistory() {
    return rollHistory.map((value, index) => <ColoredCircle key={index} rolledNumber={value} />);
  }
}
