import { Col, Row } from 'react-bootstrap';
import Container from 'react-bootstrap/esm/Container';
import Message from './components/message/Message';

export default function Admin() {
  return (
    <>
      <Container className="g-0">
        <Row className="g-2">
          <Col>
            <Message />
          </Col>
          <Col>
            <Message />
          </Col>
          <Col>
            <Message />
          </Col>
        </Row>
      </Container>
    </>
  );
}
