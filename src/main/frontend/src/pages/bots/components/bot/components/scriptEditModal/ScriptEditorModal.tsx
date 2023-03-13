import CodeEditor from '@uiw/react-textarea-code-editor';
import { useState } from 'react';
import { Button, Modal } from 'react-bootstrap';
import { useAppDispatch } from '../../../../../../redux/store';
import { patchBotScript } from '../../../../../../redux/user/bots/actions';
import { BotDto } from '../../../../../../redux/user/bots/bot.model';

export default function ScriptEditorModal(props: scriptEditorModalProps) {
  const dispatch = useAppDispatch();
  const [script, setScript] = useState(props.bot.scriptBody);

  const handleScriptSave = () => {
    dispatch(patchBotScript({ id: props.bot.id, scriptBody: script }));
    props.handleClose();
  };

  return (
    <Modal show={props.show} onHide={props.handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{props.bot.name}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <CodeEditor
          value={script}
          onChange={(event) => setScript(event.target.value)}
          language="js"
          placeholder="This will be a brain of your bot"
          data-color-mode="light"
        ></CodeEditor>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={props.handleClose}>
          Close
        </Button>
        <Button variant="success" onClick={handleScriptSave}>
          Save
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

interface scriptEditorModalProps {
  show: boolean;
  bot: BotDto;
  handleClose: () => void;
}
