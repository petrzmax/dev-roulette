import CodeEditor from '@uiw/react-textarea-code-editor';
import { useState } from 'react';
import { Button, Modal } from 'react-bootstrap';

export default function ScriptEditorModal(props: scriptEditorModalProps) {
  const [script, setScript] = useState(props.scriptBody);

  return (
    <Modal show={props.show} onHide={props.handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{props.name}</Modal.Title>
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
        <Button variant="success" onClick={props.handleClose}>
          Save
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

interface scriptEditorModalProps {
  show: boolean;
  name: string;
  scriptBody: string;
  handleClose: () => void;
}
