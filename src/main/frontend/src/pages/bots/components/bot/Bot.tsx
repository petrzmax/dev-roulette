import { useState } from 'react';
import { Badge } from 'react-bootstrap';
import { MdDelete, MdEditNote, MdPlayArrow, MdStop } from 'react-icons/md';
import { BotDto } from '../../../../redux/user/user.model';
import css from './Bot.module.scss';
import ScriptEditorModal from './components/scriptEditModal/ScriptEditorModal';

export default function Bot(props: botProps) {
  const [showScriptModal, setShowScriptModal] = useState(false);
  const handleScriptShow = () => setShowScriptModal(true);
  const handleScriptClose = () => setShowScriptModal(false);

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
          <MdDelete className={css.delete} title="Delete bot" />
        </td>
      </tr>
      <ScriptEditorModal
        name={props.bot.name}
        handleClose={handleScriptClose}
        show={showScriptModal}
        scriptBody={props.bot.scriptBody}
      />
    </>
  );
}

interface botProps {
  index: number;
  bot: BotDto;
}
