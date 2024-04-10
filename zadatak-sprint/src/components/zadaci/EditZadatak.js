import React, { useState, useCallback, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import TestAxios from '../../apis/TestAxios';

const EditZadatak = () => {
    const routeParams = useParams();
    const zadatakId = routeParams.id;
    const navigate = useNavigate();

    const [updateZadatak, setUpdateZadatak] = useState({
        zadatakId: -1,
        zadatakIme: '',
        zadatakZaduzeni: '',
        zadatakBodovi: 0,
        zadatakStanjeId: -1,
        zadatakStanjeIme: '',
        zadatakSprintId: -1,
        zadatakSprintIme: ''
    });
    const [stanja, setStanja] = useState([]);
    const [sprintovi, setSprintovi] = useState([]);

    const getZadatakById = useCallback(() => {
        TestAxios.get('/zadaci/' + zadatakId)
            .then(res => {
                // handle success
                console.log(res);
                setUpdateZadatak({
                    zadatakId: res.data.id,
                    zadatakIme: res.data.ime,
                    zadatakZaduzeni: res.data.zaduzeni,
                    zadatakBodovi: res.data.bodovi,
                    zadatakStanjeId: res.data.stanjeId,
                    zadatakStanjeIme: res.data.stanjeIme,
                    zadatakSprintId: res.data.sprintId,
                    zadatakSprintIme: res.data.sprintIme
                });
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    }, [zadatakId]);

    const getStanja = () => {
        TestAxios.get('/stanja')
            .then(res => {
                console.log(res);
                setStanja(res.data);
            })
            .catch(error => {
                console.log(error);
                alert('Error occured please try again!');
            });
    };

    const getSprintovi = () => {
        TestAxios.get('/sprintovi')
            .then(res => {
                console.log(res);
                setSprintovi(res.data);
            })
            .catch(error => {
                console.log(error);
                alert('Error occured please try again!');
            });
    };

    useEffect(() => {
        getZadatakById();
        getStanja();
        getSprintovi();
    }, [getZadatakById]);

    const onImeChange = event => {
        setUpdateZadatak({ ...updateZadatak, zadatakIme: event.target.value });
    };

    const onZaduzeniChange = event => {
        setUpdateZadatak({ ...updateZadatak, zadatakZaduzeni: event.target.value });
    };

    const onBodoviChange = event => {
        setUpdateZadatak({ ...updateZadatak, zadatakBodovi: event.target.value });
    };

    const stanjeSelectionChanged = e => {
        const input = e.target;
        setUpdateZadatak(prevState => ({
            ...prevState,
            zadatakStanjeId: input.value,
            zadatakStanjeIme: input.options[input.selectedIndex].text
        }));
    };

    const sprintSelectionChanged = e => {
        const input = e.target;
        setUpdateZadatak(prevState => ({
            ...prevState,
            zadatakSprintId: input.value,
            zadatakSprintIme: input.options[input.selectedIndex].text
        }));
    };

    const edit = () => {
        const params = {
            id: routeParams.id,
            ime: updateZadatak.zadatakIme,
            zaduzeni: updateZadatak.zadatakZaduzeni,
            bodovi: updateZadatak.zadatakBodovi,
            stanjeId: updateZadatak.zadatakStanjeId,
            stanjeIme: updateZadatak.zadatakStanjeIme,
            sprintId: updateZadatak.zadatakSprintId,
            sprintIme: updateZadatak.zadatakSprintIme
        };
        TestAxios.put('/zadaci/' + routeParams.id, params)
            .then(res => {
                // handle success
                console.log(res);
                alert('Zadatak was edited successfully!');
                navigate('/zadaci');
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    };

    return (
        <Container fluid>
            <h1>Edit zadatak</h1>
            <Row>
                <Col xs={12}>
                    <Form.Label htmlFor="zadatakIme">Ime</Form.Label>
                    <Form.Control id="zadatakIme" name="zadatakIme" type="text" value={updateZadatak.zadatakIme} onChange={onImeChange} />
                </Col>
            </Row>
            <Row>
                <Col xs={12}>
                    <Form.Label htmlFor="zadatakZaduzeni">Zaduzeni</Form.Label>
                    <Form.Control id="zadatakZaduzeni" name="zadatakZaduzeni" type="text" value={updateZadatak.zadatakZaduzeni} onChange={onZaduzeniChange} />
                </Col>
            </Row>
            <Row>
                <Col xs={12}>
                    <Form.Label htmlFor="zadatakBodovi">Bodovi</Form.Label>
                    <Form.Control id="zadatakBodovi" name="zadatakBodovi" type="number" value={updateZadatak.zadatakBodovi} onChange={onBodoviChange} />
                </Col>
            </Row>
            <Row>
                <Col xs={12}>
                    <Form.Label htmlFor="stanje">Stanja</Form.Label>
                    <Form.Control as="select" id="stanje" name="stanje" value={updateZadatak.zadatakStanjeId} onChange={stanjeSelectionChanged}>
                        <option></option>
                        {stanja.map(stanje => (
                            <option key={stanje.id} value={stanje.id}>{stanje.ime}</option>
                        ))}
                    </Form.Control>
                </Col>
            </Row>
            <Row>
                <Col xs={12}>
                    <Form.Label htmlFor="sprint">Sprintovi</Form.Label>
                    <Form.Control as="select" id="sprint" name="sprint" value={updateZadatak.zadatakSprintId} onChange={sprintSelectionChanged}>
                        <option></option>
                        {sprintovi.map(sprint => (
                            <option key={sprint.id} value={sprint.id}>{sprint.ime}</option>
                        ))}
                    </Form.Control>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Button className="button button-navy" onClick={edit}>Edit</Button>
                </Col>
            </Row>
        </Container>
    );
};

export default EditZadatak;
