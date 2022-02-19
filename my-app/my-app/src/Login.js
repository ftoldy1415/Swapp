import React from 'react';
import { useHistory } from "react-router-dom";





function Login(){
    const history = useHistory();

    const [user, setUser] = useState({email: '', palavra_passe: ''});

    function handleChange(e) {

        const name = e.target.name;
        const value = e.target.value;

        setUser( {...user, [name] : value});
    }

    function Login() {

        fetch('http://127.0.0.1:8080/api/proprietario/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(user),
        })

        .then(response => {
            return response.json()
        })
        .then((data) => {
            console.log(data.login);
            if(data.login){
                let path = '/mainPage';
                history.push(path);
            }
        })
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
                <input className = "caixasTextoWhite" id = "email" type = "email" placeholder='Insira o seu email' name = "email" value={user.email}          onChange={handleChange}/><br/>
                <input className = "caixasTextoWhite" id = "password" type = "password" placeholder='Insira a sua password' name = "password" value={user.palavra_passe}  onChange={handleChange}/>            
            </form>
        </div>

        <br/>
        <button onClick={Login}>Login</button>
    </div>
    );

}
export default Login;