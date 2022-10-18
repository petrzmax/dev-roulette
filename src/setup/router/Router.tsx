import { useRoutes } from 'react-router-dom';
import About from '../../pages/about/About';
import Bots from '../../pages/bots/Bots';
import History from '../../pages/history/History';
import Home from '../../pages/home/Home';
import Ranking from '../../pages/ranking/Ranking';
import Stats from '../../pages/stats/Stats';
import RedirectToLandingPage from './RedirectToLandingPage';

export default function Router() {
  return useRoutes([
    { path: '/', element: Home() },
    { path: '/bots', element: Bots() },
    { path: '/history', element: History() },
    { path: '/ranking', element: Ranking() },
    { path: '/stats', element: Stats() },
    { path: '/about', element: About() },

    { path: '*', element: RedirectToLandingPage() }
  ]);
}
