import { useRoutes } from "react-router-dom";
import About from "./pages/About";
import History from "./pages/History";
import Home from "./pages/Home";
import Ranking from "./pages/Ranking";
import Stats from "./pages/Stats";
import RedirectToLandingPage from "./utils/RedirectToLandingPage";

export default function Router() {
  return useRoutes([
    { path: "/", element: Home() },
    { path: "/history", element: History() },
    { path: "/ranking", element: Ranking() },
    { path: "/stats", element: Stats() },
    { path: "/about", element: About() },

    { path: "*", element: RedirectToLandingPage() },
  ]);
}
