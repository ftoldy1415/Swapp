import { keyboard } from "@testing-library/user-event/dist/keyboard";
import React, { Component } from "react";
import { useHistory } from "react-router-dom";
import {
    BrowserRouter as Router,
    Route,
    Switch,
  } from "react-router-dom";

import Login from './Login';
import MainPage from './MainPage';
import Proposal from './Proposal';
import MyProposals from './MyProposals';
import AboutUs from './AboutUs';
import LookGroup from './LookGroup';
import NewSwap from "./NewSwap";
import GruposProcura from "./GruposProcura";

import Navigation from './Navigation';

            /*
        function handleChange(e) {
    
            const name = e.target.name;
            const value = e.target.value;
    
            setUser( {...user, [name] : value});
        }
                return (
      
            <Router>
              <div>
                <Switch>
                    <Route path="/Login" component={Login}></Route>
                    <Route path="/mainPage" component={Login}></Route>

                </Switch>
              </div>
            </Router>);
        

            const history = useHistory();
  
            const routeChange = () =>{ 
              let path = `./mainPage`; 
              history.push(path);
            }
          
            return(
        
                <div className="inputs">
                    <div>
                        <img src={require("./logo.png")} class="logo" alt = "logo"/>
                        <form>
                            <input className = "caixasTextoWhite" id = "email" type = "email" placeholder='Insira o seu email' name = "email" /><br/>
                            <input className = "caixasTextoWhite" id = "password" type = "password" placeholder='Insira a sua password' name = "password"/>            
                        </form>
                    </div>
    
                    <br/>
                    <button onChange={routeChange}>Login</button>
                </div>
        );
        */
        class App extends Component {
            render() {
              return (      
                 <Router>
                  <div>
                      <Switch>
                       <Route path="/" component={Login} exact/>
                       <Route path="/MainPage" component = {MainPage}/>
                       <Route path="/Proposal/:uc" component = {Proposal}/>
                       <Route path="/MyProposals" component = {MyProposals}/>
                       <Route path="/AboutUs" component = {AboutUs}/>
                       <Route path="/LookGroup" component = {LookGroup}/>
                       <Route path="/NewSwap" component= {NewSwap}/>
                       <Route path="/GruposProcura" component={GruposProcura}/>

                      <Route component={Error}/>
                     </Switch>
                  </div> 
                </Router>
              );
            }
          }


export default App;