import { useEffect , useState} from "react";
import React from "react";
import { useHistory, useParams } from "react-router-dom";

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
                        <li><a href="">Log Out</a></li>
                    </ul>
                </nav>
            </div>
        
            <hr/>
        
            <div className="content">
                <h1>Existem as seguintes propostas</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Turno</th>
                            <th>Horas</th>
                            <th>Dia da Semana</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            turnos.map((turno) => (
                                <tr>
                                    <td>{turno.turno}</td>
                                    <td>{turno.horario}</td>
                                    <td>{turno.dia_semana}</td>
                                    <td><button>Confirmar Troca</button></td>
                                </tr>
                            ))
                        }
        
        
                    </tbody>
                </table>
                <button>Propor nova troca</button>
            </div>
        
        </div>
        
    );
}

export default Proposal