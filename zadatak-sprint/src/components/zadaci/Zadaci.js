import React, { useEffect, useState, useCallback } from 'react';
import TestAxios from '../../apis/TestAxios';
import { useNavigate } from 'react-router-dom';
import { Button, Col, Container, Form, Row, Table } from 'react-bootstrap';
import { jwtDecode } from 'jwt-decode';

const Zadaci = () => {

    const [zadaci, setZadaci] = useState([]);
    const [sprintovi, setSprintovi] = useState([]);
    const [pageNo, setPageNo] = useState(0);
    const [pageCount, setPageCount] = useState(0);
    const [searchParams, setSearchParams] = useState({
        ime: '',
        sprintId: null
    });
    //ovo je za prikaz pretrage, zadatak 2.4
    const [prikaziPretragu, setPrikaziPretragu] = useState(false);
    const token = localStorage.getItem("jwt");
    const decoded = token ? jwtDecode(token) : null;
    const isAdmin = decoded?.role?.authority === "ADMIN";
    const isKorisnik = decoded?.role?.authority === "KORISNIK";
    const navigate = useNavigate();

    const getZadaci = () => {
        console.log("dobavljam zadatke");
        TestAxios.get(`/zadaci?pageNo=${pageNo}`)
            .then(res => {
                setPageCount(res.headers["total-pages"]);
                setZadaci(res.data);
            })
            .catch(error => {
                console.log(error);
            });
    };

    const getSprintovi = useCallback(() => {
        TestAxios.get('/sprintovi')
            .then(res => {
                setSprintovi(res.data);
            })
            .catch(error => {
                console.log(error);
                alert('Error occurred please try again!');
            });
    }, []);

    useEffect(() => {
        getZadaci();
        getSprintovi();
    }, [pageNo]);

    const goToAdd = () => {
        navigate("/zadaci/add");
    };

    const promeniStanjeZadatka = (zadatakId) => {
        TestAxios.put(`/zadaci/${zadatakId}/promeni-stanje`)
            .then(res => {
                console.log(res);
                alert('Stanje zadatka je promenjeno!');
                window.location.reload();
            })
            .catch(error => {
                console.log(error);
                alert('Došlo je do greške prilikom promene stanja zadatka!');
            });
    };

    const deleteZadatak = (zadatakId) => {
        TestAxios.delete('/zadaci/' + zadatakId)
            .then(res => {
                console.log(res);
                alert('Zadatak uspešno obrisan!');
                getZadaci()   
             })
            .catch(error => {
                console.log(error);
                alert('Error occurred please try again!');
            });
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        const noviObjekat = { ...searchParams, [name]: value };
        setSearchParams(noviObjekat);
    };

    const handleSearch = () => {
        TestAxios.get(`/zadaci`,
            {
                params: {
                    ...searchParams
                }
            })
            .then(res => {
                setZadaci(res.data);
            })
            .catch(error => {
                console.log(error);
                alert('Error occurred please try again!');
            });
    };

    const cekPretraga = () => {
        setPrikaziPretragu(!prikaziPretragu);
    };

    const goToEdit = (zadatakId) => {
        navigate('/zadaci/edit/' + zadatakId);
    };

    return (
        <Container>
            <h1>Zadaci</h1>
            <Form.Group>
                {isAdmin || isKorisnik ?
                    <Form.Check
                        type="checkbox"
                        id="prikaziPretragu"
                        label="Prikaži formu za pretragu"
                        onChange={cekPretraga}
                    />
                    :
                    <></>
                }
            </Form.Group>

            {prikaziPretragu && (
                <Form>
                    <Row>
                        <Col>
                            <Form.Label>Ime zadatka</Form.Label>
                            <Form.Control type='text' name='ime' onChange={handleChange} />
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Label>Sprint</Form.Label>
                            <Form.Control as="select" name='sprintId' onChange={handleChange}>
                                <option value={null}>Izaberi</option>
                                {sprintovi.map((sprint) => {
                                    return <option key={sprint.id} value={sprint.id}>{sprint.ime}</option>;
                                })}
                            </Form.Control>
                        </Col>
                    </Row>
                    <br />
                    <Col>
                        <Button className="button button-primary" onClick={handleSearch}>Pretraži</Button>
                    </Col>

                </Form>
            )}
            <br />
            {isAdmin ?
                <Button className="button button-navy" onClick={() => goToAdd()}>Add</Button>
                :
                <></>
            }
            <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
                <Button className="btn btn-info" disabled={pageNo <= 0} onClick={() => setPageNo(pageNo - 1)}>Back</Button>
                <Button className="btn btn-info" disabled={pageNo >= pageCount - 1} onClick={() => setPageNo(pageNo + 1)}>Next</Button>
            </div>
            <br />
            <Table striped bordered hover>
                <thead>
                    <tr className="p-3 mb-2 bg-dark text-white">
                        <th>Ime</th>
                        <th>Zaduzeni</th>
                        <th>Bodovi</th>
                        <th>Stanje</th>
                        <th>Sprint</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {zadaci.map((zadatak) => {
                        return (
                            <tr key={zadatak.id}>
                                <td>{zadatak.ime}</td>
                                <td>{zadatak.zaduzeni}</td>
                                <td>{zadatak.bodovi}</td>
                                <td>{zadatak.stanjeIme}</td>
                                <td>{zadatak.sprintIme}</td>
                                <td>
                                    {isKorisnik || isAdmin ? (
                                        <>
                                            <Button className="button button-primary" onClick={() => promeniStanjeZadatka(zadatak.id)}>Predji na sledece stanje</Button>
                                            <Button className="btn btn-warning" onClick={() => goToEdit(zadatak.id)}>Edit</Button>
                                        </>
                                    ) : (
                                        <></>
                                    )}
                                    {isAdmin ?
                                        <Button className="btn btn-danger" onClick={() => deleteZadatak(zadatak.id)}>Delete</Button>
                                        :
                                        <></>
                                    }
                                </td>
                            </tr>
                        );
                    })}
                </tbody>
            </Table>
        </Container>
    );
};

export default Zadaci;
