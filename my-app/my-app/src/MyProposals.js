import { useEffect , useState } from "react";
import { useHistory} from "react-router-dom";

import './navBar.css';
import './my-proposals.css';

function MyProposals(){
    
    const history = useHistory();
    const [turnos, setTurnos] = useState([]);

    const getPropostas = async () => {
            const response = await fetch('http://127.0.0.1:8080/pedidos_utilizador', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                }
            });
            const turnoss = await response.json();
            setTurnos(turnoss);
            console.log(turnos);
    }



    function DeleteProp(uc,origem,destino){

        const dados = {
            uc : uc,
            origem : origem,
            dest : destino,
        }

        fetch('http://127.0.0.1:8080/elimina_pedido', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(dados),
        });

        window.location.reload(false);
    }

    useEffect( () => {
        getPropostas();
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
                    <h1 className="proposal-title">Your Swaps:</h1>
                    <table>
                        <thead>
                            <tr>
                                <th></th>
                                <th>From Shift</th>
                                <th>To Shift</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                turnos.map((turno) => (
                                    <tr key={turno.id}>
                                        <td>{turno.uc}</td>
                                        <td>{turno.turno_orig}</td>
                                        <td>{turno.turno_dest}</td>
                                        <td><button onClick = {() => DeleteProp(turno.uc,turno.turno_orig,turno.turno_dest)}>Delete Swap</button></td>
                                    </tr>
                                ))
                            }
            
            
                        </tbody>
                    </table>
                    <button onClick={()=> newTrade()}>Propose new Swap</button>
                </div>
            
            </div>
            
        );
}


export default MyProposals