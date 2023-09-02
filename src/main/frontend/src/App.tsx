import { GoogleOAuthProvider } from '@react-oauth/google';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect } from 'react';
import { Container } from 'react-bootstrap';
import { Toaster } from 'react-hot-toast';
import { BrowserRouter } from 'react-router-dom';
import Cookies from 'universal-cookie';
import './App.css';
import Menu from './common/components/menu/Menu';
import { ACCESS_TOKEN_COOKIE_NAME } from './common/constants';
import ServerSentEventsHandler from './events/ServerSentEventsHandler';
import { fetchRouletteData } from './redux/roulette/actions';
import { useAppDispatch } from './redux/store';
import { fetchUserData, setIsUserLoggedIn } from './redux/user/actions';
import Router from './setup/router/Router';

function App() {
  const dispatch = useAppDispatch();

  useEffect(initializeApp, []);

  function initializeApp(): void {
    const cookies = new Cookies();

    dispatch(fetchRouletteData());
    if (cookies.get(ACCESS_TOKEN_COOKIE_NAME)) {
      // TODO Validate if token is valid, before setting loggedIn flag.
      dispatch(setIsUserLoggedIn(true));
      dispatch(fetchUserData());
    }
  }

  return (
    <>
      <GoogleOAuthProvider clientId={String(import.meta.env.VITE_GOOGLE_OAUTH_CLIENT_ID)}>
        <BrowserRouter>
          <Menu />
          <Container>
            <Router />
          </Container>
        </BrowserRouter>
      </GoogleOAuthProvider>
      <Toaster position="bottom-right" toastOptions={{ duration: 5000 }} />
      <ServerSentEventsHandler />
    </>
  );
}

export default App;
