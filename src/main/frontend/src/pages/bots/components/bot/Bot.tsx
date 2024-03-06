import { useState } from 'react';
import { MdDelete, MdEditNote, MdPlayArrow, MdRestartAlt, MdStop } from 'react-icons/md';
import { BotStatus } from '../../../../common/constants';
import { useAppDispatch } from '../../../../redux/store';
import { deleteBot, updateBotStatus } from '../../../../redux/user/bots/actions';
import { BotDto, BotStatusDto } from '../../../../redux/user/bots/bot.model';
import css from './Bot.module.scss';
import BotStatusBadge from './components/botStatusBadge/BotStatusBadge';
import ScriptEditorModal from './components/scriptEditModal/ScriptEditorModal';

export default function Bot(props: botProps) {
  const dispatch = useAppDispatch();
  const [showScriptModal, setShowScriptModal] = useState(false);

  const handleScriptShow = () => setShowScriptModal(true);
  const handleScriptClose = () => setShowScriptModal(false);
  // TODO It should display "are You sure modal"
  // Should be only possible for stopped bots
  const handleDelete = () => dispatch(deleteBot(props.bot.id));
  const handleUpdateStatus = (status: BotStatus) => {
    const dto = getBotStatusDto(status);
    dispatch(updateBotStatus(dto));
  };

  const getBotStatusDto = (status: BotStatus): BotStatusDto => ({
    id: props.bot.id,
    status: status
  });

  function getStatusChangeButton() {
    switch (props.bot.status) {
      case BotStatus.READY:
        return (
          <MdPlayArrow
            className={css.start}
            title="Start processing"
            onClick={() => handleUpdateStatus(BotStatus.RUNNING)}
          />
        );
      case BotStatus.RUNNING:
        return (
          <MdStop
            className={css.stop}
            title="Stop processing"
            onClick={() => handleUpdateStatus(BotStatus.READY)}
          />
        );
      case BotStatus.FAILED:
        return <MdRestartAlt className={css.reset} title="Reset"></MdRestartAlt>;
    }
  }

  return (
    <>
      <tr>
        <td>{props.index + 1}</td>
        <td>{props.bot.name}</td>
        <td>{props.bot.balance}</td>
        <td>
          <BotStatusBadge status={props.bot.status} errorMessage={props.bot.errorMessage} />
        </td>
        <td>
          {getStatusChangeButton()}

          {props.bot.status === BotStatus.READY && (
            <>
              <MdEditNote className={css.edit} title="Edit script" onClick={handleScriptShow} />
              <MdDelete className={css.delete} title="Delete" onClick={handleDelete} />
            </>
          )}
        </td>
      </tr>
      <ScriptEditorModal show={showScriptModal} bot={props.bot} handleClose={handleScriptClose} />
    </>
  );
}

interface botProps {
  index: number;
  bot: BotDto;
}
