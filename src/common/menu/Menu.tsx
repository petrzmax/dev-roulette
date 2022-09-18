import Container from "react-bootstrap/esm/Container";
import Nav from "react-bootstrap/esm/Nav";
import Navbar from "react-bootstrap/esm/Navbar";
import { NavLink } from "react-router-dom";

export default function Menu() {
  return (
    <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand as={NavLink} to="/">
          DevRoulette
        </Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse>
          <Nav className="me-auto">
            <Nav.Link as={NavLink} to="/">
              Home
            </Nav.Link>
            <Nav.Link as={NavLink} to="/bots">
              Bots
            </Nav.Link>
            <Nav.Link as={NavLink} to="/history">
              History
            </Nav.Link>
            <Nav.Link as={NavLink} to="/ranking">
              Ranking
            </Nav.Link>
            <Nav.Link as={NavLink} to="/stats">
              Stats
            </Nav.Link>
            <Nav.Link as={NavLink} to="/about">
              About
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>

        <Navbar.Collapse className="justify-content-end">
          <Navbar.Text></Navbar.Text>
          Signed in as: <a href="#login"> Radek Brzomen</a>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
