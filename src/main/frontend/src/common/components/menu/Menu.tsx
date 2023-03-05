import { GoogleLogin } from '@react-oauth/google';
import Container from 'react-bootstrap/esm/Container';
import Nav from 'react-bootstrap/esm/Nav';
import Navbar from 'react-bootstrap/esm/Navbar';
import toast from 'react-hot-toast';
import { useSelector } from 'react-redux';
import { NavLink } from 'react-router-dom';
import { useAppDispatch } from '../../../redux/store';
import { login, logout } from '../../../redux/user/actions';
import { selectIsLoggedIn } from '../../../redux/user/selectors';
import { LOGIN_FAILED } from '../../messages';

export default function Menu() {
  const dispatch = useAppDispatch();
  const isLoggedIn = useSelector(selectIsLoggedIn);

  function loginButtonHandler(): JSX.Element {
    if (isLoggedIn) {
      return (
        <>
          <a
            href="#"
            onClick={() => {
              dispatch(logout());
            }}
          >
            Logout
          </a>
        </>
      );
    }

    return (
      <GoogleLogin
        // ux_mode="redirect"
        onSuccess={(credentialResponse) => {
          dispatch(login(credentialResponse));
        }}
        onError={() => {
          toast.error(LOGIN_FAILED);
        }}
        auto_select
      />
    );
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
            {/* TODO show if user is admin */}
            <Nav.Link as={NavLink} to="/admin">
              Admin
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
