import { useRoutes } from 'react-router-dom';
import About from '../../pages/about/About';
import Admin from '../../pages/admin/Admin';
import Bots from '../../pages/bots/Bots';
import History from '../../pages/history/History';
import Ranking from '../../pages/ranking/Ranking';
import Roulette from '../../pages/roulette/Roulette';
import Stats from '../../pages/stats/Stats';
import RedirectToHome from './RedirectToHome';

export default function Router() {
  return useRoutes([
    { path: '/', element: Roulette() },
    { path: '/bots', element: Bots() },
    { path: '/history', element: History() },
    { path: '/ranking', element: Ranking() },
    { path: '/stats', element: Stats() },
    { path: '/about', element: About() },
    { path: '/admin', element: Admin() },

    { path: '*', element: RedirectToHome() }
  ]);
}
