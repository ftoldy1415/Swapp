import  { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";

import './navBar.css';
import './groups.css';
import './styles.css';


function GruposProcura(){

    let history = useHistory();
    const [groups, setGroups] = useState([]);
    const [turnos, setTurnos] = useState([]);

    const [UC, setCadeira] = useState(1);

    let cadeiras = [];

    const getGroups = async () => {
        const response = await (await fetch('http://127.0.0.1:8080/pedir_grupo', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body : JSON.stringify({uc: UC})
        }).then(response => response.json()));
        setGroups(response);
        console.log(groups);
    }

    async function getTurnos(){
        const response = await fetch('http://127.0.0.1:8080/getUcs', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        });
        const turnoss = await response.json();
        console.log(turnoss);
        setTurnos(turnoss);

        for (let i = 0 ; i<turnos.length; i++){
            cadeiras.append(turnos[i].uc);
        }
    }
/*
    useEffect( () => {
        getGroups();
    });
*/


    /*
    useEffect(() => {
        getTurnos();
    },[])
    */


    function confirmReq(id){

        fetch('http://127.0.0.1:8080/confirma_proposta', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body : JSON.stringify({id} )
        });

        let path = '/MainPage';
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


            <div className="main-body">
                <div className="wrapper">
                    <h1 className="post-title">Select Course</h1><br></br>
                    <div className="input-data">
                        <input type="text" required name = "uc" value={UC} onChange={ (e) => setCadeira(e.target.value)}></input>
                        <div className="underline"></div>
                    </div>
                    <br></br>
                    <button className="pipi-button center-button" onClick= {()=> getGroups()}>Submit</button>
                </div>
            </div>

            <div className="content">
            <table>
                <thead>
                    <tr>
                        <th>Members sought</th>
                        <th>Course</th>
                        <th>Description</th>
                        <th>Notify?</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        groups.map((grupo) => (
                            <tr>
                                <td>{grupo.num_pessoas}</td>
                                <td>{grupo.uc}</td>
                                <td>{grupo.descricao}</td>
                                <td><button onClick = { ()=> confirmReq(grupo.id)}>Yes</button></td>

                            </tr>
                        ))
                    }
    
    
                </tbody>
            </table>
            </div>

        </div>
    );
}

export default GruposProcura;