import { Button, Card, Form, InputGroup } from "react-bootstrap";

export default function AmountPanel() {
  return (
    <Card bg="light">
      <Card.Body>
        <Card.Title className="text-start">Balance: 5000</Card.Title>
        <InputGroup className="mb-2">
          <Button variant="outline-primary">Clear</Button>
          <Button variant="outline-primary">Last</Button>
          <Button variant="outline-primary">+1</Button>
          <Button variant="outline-primary">+10</Button>
          <Button variant="outline-primary">+100</Button>
          <Button variant="outline-primary">+1000</Button>
          <Button variant="outline-primary">1/2</Button>
          <Button variant="outline-primary">x2</Button>
          <Button variant="outline-primary">Max</Button>
        </InputGroup>
        <Form.Control type="number" placeholder="Bet amount..." />
      </Card.Body>
    </Card>
  );
}
