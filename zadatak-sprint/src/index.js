import React from 'react';
import { createRoot } from 'react-dom/client';
import { Route, Link, Navigate, BrowserRouter as Router, Routes } from 'react-router-dom';
import { Container, Nav, Navbar, Button } from 'react-bootstrap';
import NotFound from './components/NotFound';
import Home from './components/Home';
import Zadaci from './components/zadaci/Zadaci';
import Login from './components/authorization/Login';
import { logout } from './services/auth';
import AddZadatak from './components/zadaci/AddZadatak';
import EditZadatak from './components/zadaci/EditZadatak';

const App = () => {

    if (window.localStorage["jwt"]) {
        return (
            <>
                <Router>
                     <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                            TEST
                        </Navbar.Brand>
                        <Nav>
                            <Nav.Link as={Link} to="/zadaci">
                                Zadaci
                            </Nav.Link>
                            <Button onClick={logout}>Logout</Button>
                        </Nav>
                    </Navbar>
                    <Container style={{paddingTop:"10px"}}>
                        <Routes>
                            <Route path="/" element={<Home />} />
                            <Route path="/login" element={<Navigate replace to = "/zadaci" />} />
                            <Route path="/zadaci" element={<Zadaci />} />
                            <Route path="/zadaci/add" element={<AddZadatak />} />
                            <Route path="/zadaci/edit/:id" element={<EditZadatak />} />
                            <Route path="*" element={<NotFound />} />
                        </Routes>
                    </Container>
                </Router>
            </>

        );
    } else {
        return (
            <>
             <Router>
                    <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                            TEST
                        </Navbar.Brand>
                        <Nav>
                            <Nav.Link as={Link} to="/zadaci">
                                Zadaci
                            </Nav.Link>
                        </Nav>
                        <Nav>
                            <Nav.Link as={Link} to="/login">
                                Login
                            </Nav.Link>
                        </Nav>
                    </Navbar>
                    
                    <Container style={{paddingTop:"10px"}}>
                        <Routes>
                            <Route path="/" element={<Home />} />
                            <Route path="/zadaci" element={<Zadaci />} />
                            <Route path="/login" element={<Login />}/>
                            <Route path="*" element={<Navigate replace to = "/login" />} />
                        </Routes>
                    </Container>
                </Router>
            </>

        )
    }


};

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
    <App />,
); 