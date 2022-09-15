import { Button, Card } from "react-bootstrap";

export default function ColorBetSelectorPanel(
  props: colorBetSelectorPanelProps
) {
  return (
    <Card bg="light">
      <Button variant="danger" className="mx-3 my-2">
        {props.text}
      </Button>
    </Card>
  );
}

interface colorBetSelectorPanelProps {
  text: string;
  color: string;
}
