import React, { useCallback, useEffect, useState } from 'react';
import { Row, Col, Form, Button } from "react-bootstrap";
import TestAxios from '../../apis/TestAxios';
import { useNavigate } from 'react-router-dom';

const AddZadatak = () => {
    const navigate = useNavigate();

    const [zadatak, setZadatak] = useState({
        zIme: "",
        zZaduzeni: "",
        zBodovi: 0,
        zStanje: null,
        zSprint: null
    });

    const [stanja, setStanja] = useState([]);
    const [sprintovi, setSprintovi] = useState([]);

    const getStanja = useCallback(() => {
        TestAxios.get('/stanja')
            .then(res => {
                setStanja(res.data);
            })
            .catch(error => {
                console.log(error);
                alert('Error occurred please try again!');
            });
    }, []);

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
        getStanja();
        getSprintovi();
    }, []);

    const valueInputChanged = (e) => {
        const { name, value } = e.target;
        setZadatak(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const stanjeSelectionChanged = (e) => {
        const value = e.target.value;
        const stanje = stanja.find(stanje => stanje.id == value);
        setZadatak(prevState => ({
            ...prevState,
            zStanje: stanje
        }));
    };

    const sprintSelectionChanged = (e) => {
        const value = e.target.value;
        const sprint = sprintovi.find(sprint => sprint.id == value);
        setZadatak(prevState => ({
            ...prevState,
            zSprint: sprint
        }));
    };

    const create = () => {
        const { zIme, zZaduzeni, zBodovi, zStanje, zSprint } = zadatak;
        const params = {
            ime: zIme,
            zaduzeni: zZaduzeni,
            bodovi: zBodovi,
            stanjeId: zStanje.id,
            stanjeIme: zStanje.ime,
            sprintId: zSprint.id,
            sprintIme: zSprint.ime
        };

        TestAxios.post('/zadaci', params)
            .then(res => {
                navigate('/zadaci');
            })
            .catch(error => {
                console.log(error);
            });
    };

    return (
        <Row>
            <Col></Col>
            <Col xs="12" sm="12" md="12">
                <h1>Dodaj zadatak</h1>
                <Form>
                    <Form.Group>
                        <Form.Label>Ime</Form.Label>
                        <Form.Control
                            id="zIme"
                            name="zIme"
                            onChange={valueInputChanged}
                            value={zadatak.zIme}
                        />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Zaduzeni</Form.Label>
                        <Form.Control
                            id="zZaduzeni"
                            name="zZaduzeni"
                            onChange={valueInputChanged}
                            value={zadatak.zZaduzeni}
                        />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Bodovi</Form.Label>
                        <Form.Control
                            required
                            type="number"
                            id="zBodovi"
                            name="zBodovi"
                            onChange={valueInputChanged}
                            value={zadatak.zBodovi}
                        />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Stanje</Form.Label>
                        <Form.Control
                            as="select"
                            name="zStanje"
                            onChange={stanjeSelectionChanged}
                            value={zadatak.zStanje ? zadatak.zStanje.id : ''}
                        >
                            <option>Izaberi</option>
                            {stanja.map(stanje => (
                                <option key={stanje.id} value={stanje.id}>{stanje.ime}</option>
                            ))}
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Sprint</Form.Label>
                        <Form.Control
                            as="select"
                            name="zSprint"
                            onChange={sprintSelectionChanged}
                            value={zadatak.zSprint ? zadatak.zSprint.id : ''}
                        >
                            <option>Izaberi</option>
                            {sprintovi.map(sprint => (
                                <option key={sprint.id} value={sprint.id}>{sprint.ime}</option>
                            ))}
                        </Form.Control>
                    </Form.Group>
                    <Button onClick={create}>Dodaj</Button>
                </Form>
            </Col>
            <Col></Col>
        </Row>
    );
};

export default AddZadatak;
