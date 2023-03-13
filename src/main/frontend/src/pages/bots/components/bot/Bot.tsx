import { useState } from 'react';
import { Badge } from 'react-bootstrap';
import { MdDelete, MdEditNote, MdPlayArrow, MdStop } from 'react-icons/md';
import { useAppDispatch } from '../../../../redux/store';
import { deleteBot } from '../../../../redux/user/bots/actions';
import { BotDto } from '../../../../redux/user/bots/bot.model';
import css from './Bot.module.scss';
import ScriptEditorModal from './components/scriptEditModal/ScriptEditorModal';

export default function Bot(props: botProps) {
  const dispatch = useAppDispatch();
  const [showScriptModal, setShowScriptModal] = useState(false);

  const handleScriptShow = () => setShowScriptModal(true);
  const handleScriptClose = () => setShowScriptModal(false);
  // TODO It should display "are You sure modal"
  // Should be only possible for stopped bots
  const handleDelete = () => dispatch(deleteBot(props.bot.id));

  return (
    <>
      <tr>
        <td>{props.index + 1}</td>
        <td>{props.bot.name}</td>
        <td>{props.bot.balance}</td>
        <td>
          <Badge bg="success">Stable</Badge>
        </td>
        <td>
          <MdPlayArrow className={css.start} title="Start bot processing" />
          <MdStop className={css.stop} title="Stop bot processing" />
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
