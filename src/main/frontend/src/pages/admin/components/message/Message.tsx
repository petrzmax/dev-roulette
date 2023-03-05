import { useState } from 'react';
import { Button, Card, Form } from 'react-bootstrap';
import { dispatchMessage } from '../../../../redux/admin/actions';
import { useAppDispatch } from '../../../../redux/store';

export default function Message() {
  const dispatch = useAppDispatch();
  const [message, setMessage] = useState('');
  const [messageType, setMessageType] = useState('message');

  function handleMessageChange(event: React.ChangeEvent<HTMLInputElement>) {
    setMessage(event.target.value);
  }

  function handleTypeChange(event: React.ChangeEvent<HTMLSelectElement>) {
    setMessageType(event.target.value);
  }

  function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    dispatch(dispatchMessage({ message: message, type: messageType }));
    event.preventDefault();
  }

  return (
    <Card bg="light">
      <Card.Body>
        <Card.Title className="text-start">Dispatch message</Card.Title>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3" controlId="formMessage">
            <Form.Label>Message</Form.Label>
            <Form.Control type="text" placeholder="Enter message" onChange={handleMessageChange} />
            <Form.Text>This message will be displayed to each client in toast.</Form.Text>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formType">
            <Form.Label>Type</Form.Label>
            <Form.Select onChange={handleTypeChange}>
              <option value="message">Message</option>
              <option value="success">Success</option>
              <option value="warning">Warning</option>
            </Form.Select>
          </Form.Group>
          <Button variant="primary" type="submit">
            Dispatch
          </Button>
        </Form>
      </Card.Body>
    </Card>
  );
}
