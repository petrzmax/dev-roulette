import { Button, Card } from "react-bootstrap";

export default function ManualBetPanel(props: ManualBetPanelProps) {
  return (
    <Card bg="light">
      <Button variant="danger" className="mx-3 my-2">
        {props.text}
      </Button>
    </Card>
  );
}

interface ManualBetPanelProps {
  text: string;
  color: string;
}
