import { GoogleLogin } from '@react-oauth/google';
import Container from 'react-bootstrap/esm/Container';
import Nav from 'react-bootstrap/esm/Nav';
import Navbar from 'react-bootstrap/esm/Navbar';
import { useSelector } from 'react-redux';
import { NavLink } from 'react-router-dom';
import { login, logout } from '../../../redux/actions/userActions';
import { selectIsLoggedIn } from '../../../redux/reducers/userReducer';
import { useAppDispatch } from '../../../redux/store';

export default function Menu() {
  const dispatch = useAppDispatch();
  const isLoggedIn = useSelector(selectIsLoggedIn);

  function loginButtonHandler(): JSX.Element {
    if (isLoggedIn) {
      return (
        <a href="#" onClick={() => dispatch(logout())}>
          Logout
        </a>
      );
    } else {
      return (
        <GoogleLogin
          // ux_mode="redirect"
          onSuccess={(credentialResponse) => {
            dispatch(login(credentialResponse));
          }}
          onError={() => {
            console.log('Login Failed');
          }}
          auto_select
        />
      );
    }
  }

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

          {loginButtonHandler()}
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
