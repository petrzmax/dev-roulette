import "bootstrap/dist/css/bootstrap.min.css";
import { Container } from "react-bootstrap";
import { BrowserRouter } from "react-router-dom";
import "./App.css";
import Menu from "./Menu";
import Router from "./route-config";

function App() {
  return (
    <BrowserRouter>
      <Menu />
      <Container>
        <Router />
      </Container>
    </BrowserRouter>
  );
}

export default App;
