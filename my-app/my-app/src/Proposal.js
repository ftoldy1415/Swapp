import { useEffect , useState} from "react";
import React from "react";
import { useHistory, useParams } from "react-router-dom";

import './navBar.css';
import './proposal.css';

function Proposal(){
    const {uc} = useParams();

    let history = useHistory();
    const [turnos, setTurnos] = useState([]);


    const getTurnos = async () => {
        const response = await fetch('http://127.0.0.1:8080/possiveisTrocas', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({uc}),
        });
        const turnoss = await response.json();
        setTurnos(turnoss);
        console.log(turnos);
    }


    async function sendTrade(turnoSend){
        const info = {
            turno : turnoSend
        }
        await fetch('http://127.0.0.1:8080/efetuaTroca', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(info),
        });
        window.location.reload(false);

    }

    useEffect( () => {
        getTurnos();
    },[]);

    function newTrade(){
        let path = '/NewSwap';
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
                <h1 className="proposal-title">Available Offers:</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Shift</th>
                            <th>Hour</th>
                            <th>Day</th>
                            <th>Has Conflict?</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            turnos.map((turno) => (
                                <tr key = {turno.id}>
                                    <td>{turno.turno}</td>
                                    <td>{turno.horario}</td>
                                    <td>{turno.dia_semana}</td>

                                    <td>{turno.sobreposicao}</td>

                                    <td><button onClick = {() => {sendTrade(turno.turno)}}>Confirm Swap</button></td>
                                </tr>
                            ))
                        }
        
        
                    </tbody>
                </table>
                <br></br>
                <button onClick={()=> newTrade()}>Propose new Swap</button>
            </div>
        
        </div>
        
    );
}

export default Proposal