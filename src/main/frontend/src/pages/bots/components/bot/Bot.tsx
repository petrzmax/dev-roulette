import { useState } from 'react';
import { MdDelete, MdEditNote, MdPlayArrow, MdStop } from 'react-icons/md';
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
          <MdPlayArrow
            className={css.start}
            title="Start bot processing"
            onClick={() => handleUpdateStatus(BotStatus.RUNNING)}
          />
          <MdStop
            className={css.stop}
            title="Stop bot processing"
            onClick={() => handleUpdateStatus(BotStatus.READY)}
          />
          <MdEditNote className={css.edit} title="Edit bot script" onClick={handleScriptShow} />
          <MdDelete className={css.delete} title="Delete bot" onClick={handleDelete} />
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
