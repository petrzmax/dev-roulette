import { useContext } from "react";
import { Button, Card } from "react-bootstrap";
import { BetType } from "../../../../common/constants";
import { postRouletteBet } from "../../../../redux/actions/rouletteActions";
import { useAppDispatch } from "../../../../redux/store";
import { BetContext } from "../../context/BetContext";

export default function ManualBetPanel(props: ManualBetPanelProps) {
  const dispatch = useAppDispatch();
  const { betAmount, setBetAmount } = useContext(BetContext);

  function makeBet(): void {
    dispatch(postRouletteBet({ amount: betAmount, betType: props.betType }));
  }

  return (
    <Card bg="light">
      <Button variant="danger" className="mx-3 my-2" onClick={makeBet}>
        {props.text}
      </Button>
    </Card>
  );
}

interface ManualBetPanelProps {
  text: string;
  betType: BetType;
}
