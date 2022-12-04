import { useContext, useRef } from 'react';
import { Button, Card, Form, InputGroup } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { RootState } from '../../../../redux/store';
import { BetContext } from '../../context/BetContext';

export default function BetAmountPanel() {
  const { betAmount, setBetAmount } = useContext(BetContext);

  const selectBalance = useSelector((state: RootState) => state.session.balance);

  const amountInputRef = useRef<any>();

  const handleAmountChange = (event: any) => {
    setBetAmount(parseInt(event.target.value));
  };

  const clearAmountInput = () => {
    setBetAmount(0);
    amountInputRef.current.value = '';
  };

  return (
    <Card bg="light">
      <Card.Body>
        <Card.Title className="text-start">Balance: {selectBalance}</Card.Title>
        <InputGroup className="mb-2">
          <Button variant="outline-primary" onClick={clearAmountInput}>
            Clear
          </Button>
          <Button variant="outline-primary">Last</Button>
          <Button variant="outline-primary" onClick={() => setBetAmount(betAmount + 1)}>
            +1
          </Button>
          <Button variant="outline-primary" onClick={() => setBetAmount(betAmount + 10)}>
            +10
          </Button>
          <Button variant="outline-primary" onClick={() => setBetAmount(betAmount + 100)}>
            +100
          </Button>
          <Button variant="outline-primary" onClick={() => setBetAmount(betAmount + 1000)}>
            +1000
          </Button>
          <Button variant="outline-primary" onClick={() => setBetAmount(betAmount / 2)}>
            1/2
          </Button>
          <Button variant="outline-primary" onClick={() => setBetAmount(betAmount * 2)}>
            x2
          </Button>
          <Button variant="outline-primary" onClick={() => setBetAmount(selectBalance)}>
            Max
          </Button>
        </InputGroup>

        <Form.Control
          type="number"
          placeholder="Bet amount..."
          value={betAmount}
          onChange={handleAmountChange}
          min="0"
          max={selectBalance}
          step={1}
          ref={amountInputRef}
        />
      </Card.Body>
    </Card>
  );
}
