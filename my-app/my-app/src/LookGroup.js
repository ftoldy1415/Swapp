import React, {useState} from 'react';


import './navBar.css';
import './groups.css';

function LookGroup(){

    const [ numMembros , setMembros] = useState('');
    const [ descricao , setDescricao] = useState('');
    const [ uc , setUc] = useState('');
    const [ numTelemovel , setNumtelemovel] = useState('')
 

    function submeter(){
        let data1 = {
            num_pessoas : numMembros,
            descricao : descricao,
            uc : uc
        };

        fetch('http://127.0.0.1:8080/anunciar_grupo', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data1),
        })


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
                    <h1 className="post-title">Post your Advert!</h1><br></br>
                    <div className="input-data">
                        <input type="text" required name = "numMembros" value={numMembros} onChange={ (e) => setMembros(e.target.value)}></input>
                        <div className="underline"></div>
                        <label>How many members you need</label>
                    </div>
                    <br></br>
                    <div className="input-data">
                        <input type="text" required name = "numTelemovel" value={numTelemovel} onChange={ (e) => setNumtelemovel(e.target.value)}></input>
                        <div className="underline"></div>
                        <label>Phone Number</label>
                    </div>
                    <br></br>
                    <div className="input-data">
                        <input type="text" required name = "uc" value={uc} onChange={ (e) => setUc(e.target.value)}></input>
                        <div className="underline"></div>
                        <label>Course Abreviation</label>
                    </div>
                    <br></br>
                    <label className="input-data">Additional Information:</label>
                    <div className="coments">
                        <textarea className="w3review" name="descricao" value={descricao} onChange={ (e) => setDescricao(e.target.value)} rows="4" cols="50"></textarea>
                    </div>
                    <br></br>
                    <button className="pipi-button center-button" onClick = {()=> submeter()}>Submit</button>
                </div>
            </div>

                

            </body>
        </>
    );
}

export default LookGroup