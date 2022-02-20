import React, {useState} from 'react';
import { useHistory} from "react-router-dom";

import './navBar.css';
import './groups.css'
import './login.css';

function Login(){
    const history = useHistory();
    const [ email , setEmail] = useState('');
    const [ password , setPassword] = useState('');

    function Login() {

        var data1 = {
            email : email,
            password : password
        };

        fetch('http://127.0.0.1:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data1),
        })

        .then(response => response.json())
        .then((data) => {
            console.log(typeof(data.login));
            if(data.login === 'true'){
                let path = '/MainPage';
                history.push(path);
            }
            else{
                console.log(data.login);
            }
        })
        .catch(error => console.log(error));
    }

    /*
    function openSite(){
        console.log("coiso");
        let path = '/mainPage';
        history.push(path);
    }
    */
    
    return(<div className="inputs">
        <div className="main-body">
            <div className="wrapper">
            <img src={require("./logo_preto.png")} className="logo-login" alt = "logo"/>
                <h1 className="post-title">Welcome!</h1><br></br>
                <br></br>
                <div className="input-data">
                    <input type="text" name = "email" value={email} onChange={ (e) => setEmail(e.target.value)}/><br/>
                    <div className="underline"></div>
                    <label>Email</label>
                </div>
                <br></br>
                <div className="input-data">
                    <input type="password" required name = "password" value={password}  onChange={(e) => setPassword(e.target.value)}/>
                    <div className="underline"></div>
                    <label>Password</label>
                </div>
                <br></br>
                <button className="pipi-button center-button" onClick={Login}>Login</button>
            </div>
        </div>
    </div>);

}
export default Login;