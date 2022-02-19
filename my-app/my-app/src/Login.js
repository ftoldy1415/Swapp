import React, {useState} from 'react';
import { useHistory} from "react-router-dom";


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
            console.log(data.login)
            let path = '/mainPage';
            history.push(path);
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
    
    return(
        
        <div className="inputs">
        <div>
            <img src={require("./logo.png")} className="logoLogin" alt = "logo"/>
            <form>
                <input className = "caixasTextoWhite" id = "email" type = "email" placeholder='Insira o seu email' name = "email" value={email}          onChange={ (e) => setEmail(e.target.value)}/><br/>
                <input className = "caixasTextoWhite" id = "password" type = "password" placeholder='Insira a sua password' name = "password" value={password}  onChange={(e) => setPassword(e.target.value)}/>            
            </form>
        </div>

        <br/>
        <button onClick={Login}>Login</button>
    </div>
    );

}
export default Login;