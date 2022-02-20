import React from 'react';
import { useHistory } from "react-router-dom";
import { useEffect, useRef, useState } from 'react';

import './navBar.css';
import './styles.css';

function MainPage() {

    let history = useHistory();
    const [turnos, setTurnos] = useState([]);

    const getTurnos = async () => {
        const response = await fetch('http://127.0.0.1:8080/getTurnos', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        });
        const turnoss = await response.json();
        setTurnos(turnoss);
    }

    useEffect(() => {
        getTurnos();
    }, []);

    function sendResponse(cadeira) {
        const info = {
            uc: cadeira,
        };
/*
        fetch('http://127.0.0.1:8080/getTurnos', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });
    */
        
        let path = `./Proposal/${cadeira}`;
        history.push(path);
    }

    return(
    <div>
        <div className=".header">
            <nav>
                <img src={require("./logo.png")}  alt="logo" className="logo"/>
                <ul className="nav-links">
                    <li><a href="/MainPage">Home</a></li>
                    <li><a href="/LookGroup">Looking for Groups</a></li>
                    <li><a href="/GruposProcura">Find Group</a></li>
                    <li><a href="/MyProposals">My Swaps</a></li>
                    <li><a href="/AboutUs">About</a></li>
                    <li><a href="/">Log Out</a></li>
                </ul>
            </nav>
        </div>

        <div className="content">
            <h1 className="proposal-title">Your Courses:</h1>
            <table>
                <thead>
                    <tr>
                        <th>Course</th>
                        <th>Shift</th>
                        <th>Status</th>
                        <th>Settings</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        turnos.map((turno) => (
                            <tr key = {turno.id}>
                                <td><a href="https://www.uminho.pt/PT/ensino/oferta-educativa/_layouts/15/UMinho.PortalUM.UI/Pages/CatalogoCursoDetail.aspx?itemId=4079&catId=12">{turno.uc}</a></td>
                                <td>{turno.num_turno}</td><td><p className="status status-waiting">Waiting</p></td>
                                <td><button onClick={()=>sendResponse(turno.uc)}>Try to Swap</button></td>
                            </tr> 
                        ))
                    }

                </tbody>
            </table>
        </div>

    </div>

    );
}

export default MainPage;