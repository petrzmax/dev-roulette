import { useContext } from 'react';
import { Button, Card } from 'react-bootstrap';
import { BetType } from '../../../../common/constants';
import { postRouletteBet } from '../../../../redux/roulette/actions';
import { useAppDispatch } from '../../../../redux/store';
import { BetContext } from '../../context/BetContext';

export default function ManualBetPanel(props: manualBetPanelProps) {
  const dispatch = useAppDispatch();
  const { betAmount } = useContext(BetContext);

  function makeBet(): void {
    dispatch(postRouletteBet({ amount: betAmount, betType: props.betType }));
  }

  function getVariant(): string {
    switch (props.betType) {
      case BetType.GREEN: {
        return 'success';
      }
      case BetType.RED: {
        return 'danger';
      }
      case BetType.BLACK: {
        return 'dark';
      }
      default: {
        return 'warning';
      }
    }
  }

  return (
    <Card bg="light">
      <Button variant={getVariant()} className="mx-3 my-2" onClick={makeBet}>
        {props.text}
      </Button>
    </Card>
  );
}

interface manualBetPanelProps {
  text: string;
  betType: BetType;
}
