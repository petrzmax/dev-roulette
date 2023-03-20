import { ReactElement } from 'react';
import { Badge } from 'react-bootstrap';
import { BotStatus } from '../../../../../../common/constants';
import css from './BotStatusBadge.module.scss';

export default function BotStatusBadge(props: BotStatusBadgeProps) {
  function getBadge(): ReactElement {
    switch (props.status) {
      case BotStatus.READY:
        return (
          <Badge className={css.badge} bg="warning">
            Ready
          </Badge>
        );

      case BotStatus.RUNNING:
        return (
          <Badge className={css.badge} bg="success">
            Running
          </Badge>
        );

      case BotStatus.FAILED:
        return (
          <Badge className={css.badge} bg="danger" title={props.errorMessage}>
            Failed
          </Badge>
        );

      default:
        return (
          <Badge className={css.badge} bg="secondary">
            default
          </Badge>
        );
    }
  }

  return getBadge();
}

interface BotStatusBadgeProps {
  status: BotStatus;
  errorMessage: string;
}
