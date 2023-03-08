import { useState } from 'react';
import { Button, Form, Modal } from 'react-bootstrap';
import { useAppDispatch } from '../../../../redux/store';
import { createBot } from '../../../../redux/user/bots/actions';

export default function AddBotModal(props: AddBotModalProps) {
  const dispatch = useAppDispatch();
  const [name, setName] = useState('');

  function handleNameChange(event: React.ChangeEvent<HTMLInputElement>) {
    setName(event.target.value);
  }

  function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    dispatch(createBot({ name: name }));
    props.handleClose();
    event.preventDefault();
  }

  return (
    <Modal show={props.show} onHide={props.handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>Add bot</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form id="bot-form" onSubmit={handleSubmit}>
          <Form.Label>Name</Form.Label>
          <Form.Control type="text" placeholder="Enter name" onChange={handleNameChange} />
          <Form.Text>Bot name cannot be changed later.</Form.Text>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={props.handleClose}>
          Cancel
        </Button>
        <Button variant="success" type="submit" form="bot-form">
          Add
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

interface AddBotModalProps {
  show: boolean;
  handleClose: () => void;
}
