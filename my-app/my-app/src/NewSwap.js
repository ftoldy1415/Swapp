import React, {useState} from 'react';
import { useHistory} from "react-router-dom";

import './navBar.css';
import './groups.css';

function NewSwap(){

    const history = useHistory();
    const [ uc , setUc] = useState('');
    const [ turno_dest , setTurno_dest] = useState('');

    async function SubmitSwap(){
        var data1 = {
            uc : uc,
            turno_dest : turno_dest
        };
        console.log(uc);
        console.log(turno_dest);
        await fetch('http://127.0.0.1:8080/anunciaTroca', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data1),
        })
        .catch(error => console.log(error));

        
        let path = '/MyProposals';
        history.push(path);
    }


    return (
        <>
            <body>
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
                    <h1 className="post-title">New Swap</h1><br></br>
                    <div className="input-data">
                        <input type="text" required name = "uc" value={uc} onChange={ (e) => setUc(e.target.value)}></input>
                        <div className="underline"></div>
                        <label>Course Abreviation</label>
                    </div>
                    <br></br>
                    <div className="input-data">
                        <input type="text" required name = "turno_dest" value={turno_dest} onChange={ (e) => setTurno_dest(e.target.value)}></input>
                        <div className="underline"></div>
                        <label>Shift</label>
                    </div>
                    <br></br>
                    <button className="pipi-button center-button" onClick = {() =>  SubmitSwap()} >Submit</button>
                </div>
            </div>

                

            </body>
        </>
    );
}

export default NewSwap