import React from 'react';
import { useHistory } from "react-router-dom";
import {useEffect, useRef, useState} from 'react';


function MainPage(){


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

    useEffect( () => {
        getTurnos();
    },[]);

    

    return(
    <div>
        <div className=".header">
            <nav>
                <img src={require("./logo.png")}  alt="logo" className="logo"/>
                <ul className="nav-links">
                    <li><a href="">Home</a></li>
                    <li><a href="">Groups</a></li>
                    <li><a href="">See Swaps</a></li>
                    <li><a href="/">Log Out</a></li>
                </ul>
            </nav>
        </div>

        <hr/>

        <div className="content">
            <table>
                <thead>
                    <tr>
                        <th>UC</th>
                        <th>Turno</th>
                        <th>Status</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        turnos.map((turno) => (
                            <tr key = {turno.id}>
                                <td><a href="https://www.uminho.pt/PT/ensino/oferta-educativa/_layouts/15/UMinho.PortalUM.UI/Pages/CatalogoCursoDetail.aspx?itemId=4079&catId=12">{turno.uc}</a></td>
                                <td>{turno.num_turno}</td><td><p className="status status-waiting">Waiting</p></td>
                                <td><button>Postar Pedido</button></td>
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