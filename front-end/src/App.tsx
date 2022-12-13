import { GoogleOAuthProvider } from '@react-oauth/google';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect } from 'react';
import { Container } from 'react-bootstrap';
import { BrowserRouter } from 'react-router-dom';
import Cookies from 'universal-cookie';
import './App.css';
import Menu from './common/components/menu/Menu';
import { ACCESS_TOKEN_COOKIE_NAME } from './common/constants';
import { setIsUserLoggedIn } from './redux/actions/sessionActions';
import { useAppDispatch } from './redux/store';
import Router from './setup/router/Router';

function App() {
  const dispatch = useAppDispatch();

  useEffect(initialize, []);

  function initialize(): void {
    const cookies = new Cookies();
    if (cookies.get(ACCESS_TOKEN_COOKIE_NAME)) {
      dispatch(setIsUserLoggedIn(true));
    }
  }

  return (
    <GoogleOAuthProvider clientId={String(process.env.REACT_APP_GOOGLE_OAUTH_CLIENT_ID)}>
      <BrowserRouter>
        <Menu />
        <Container>
          <Router />
        </Container>
      </BrowserRouter>
    </GoogleOAuthProvider>
  );
}

export default App;
