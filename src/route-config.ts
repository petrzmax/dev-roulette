import { useRoutes } from "react-router-dom";
import About from "./About";
import Home from "./Home";

export default function Router() {
  return useRoutes([
    //{ path: "/genres", element: IndexGenres() },
    //{ path: "/stats", element: IndexGenres() },
    { path: "/about", element: About() },

    { path: "/", element: Home() },

    //{ path: "*", element: RedirectToLandingPage() },
  ]);
}
