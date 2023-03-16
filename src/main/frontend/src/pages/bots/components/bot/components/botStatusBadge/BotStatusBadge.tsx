import { ReactElement } from 'react';
import { Badge } from 'react-bootstrap';
import { BotStatus } from '../../../../../../common/constants';

export default function BotStatusBadge(props: BotStatusBadgeProps) {
  function getBadge(): ReactElement {
    switch (props.status) {
      case BotStatus.READY:
        return <Badge bg="warning">Ready</Badge>;

      case BotStatus.RUNNING:
        return <Badge bg="success">Running</Badge>;

      case BotStatus.FAILED:
        return (
          <Badge bg="danger" title={props.errorMessage}>
            Failed
          </Badge>
        );

      default:
        return <Badge bg="secondary">default</Badge>;
    }
  }

  return getBadge();
}

interface BotStatusBadgeProps {
  status: BotStatus;
  errorMessage: string;
}
