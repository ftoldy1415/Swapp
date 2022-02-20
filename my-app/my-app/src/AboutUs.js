

import './navBar.css';
import './about.css';

function AboutUs(){
    return (
        <div>
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
                
                <div className="about-section">
                    <h1 className="titulo">About Us</h1>
                    <p>Este programa foi desenvolvido no âmbito do Hackathon da Semana da Engenharia informática 2022. Tem como propósito o melhoramento do sistema pré existente "Swap" utilizado pelo departamento de Informática na distribuição e troca de turnos entre os seus alunos. Este projeto foi desenvolvido pela seguinte equipa:</p>
                </div>

                <div className="row">
                    <div className="column">
                    <div className="card">
                        <img src={require("./images/caster.png")} className="card" alt="Jane"/>
                        <div className="container">
                        <h2>João Delgado</h2>
                        <p className="title">Responsável pelo Backend</p>
                        <p>Aluno do 3º ano de LEI</p>
                        <p>https://github.com/delgas12</p>
                        <p><button className="button" onClick={()=> window.open('https://github.com/delgas12')}>Contact</button></p>
                        <br></br>
                        </div>
                    </div>
                    </div>
                
                    <div className="column">
                    <div className="card">
                        <img src={require("./images/bruno.jfif")} className="card" alt="Jane"/>
                        <div className="container">
                        <h2>Bruno Pereira</h2>
                        <p className="title">Responsável pelo Backend</p>
                        <p>Aluno do 3º ano de LEI</p>
                        <p>https://github.com/obrunofilipe</p>
                        <p><button className="button" onClick={()=> window.open('https://github.com/obrunofilipe')}>Contact</button></p>
                        <br></br>
                        </div>
                    </div>
                    </div>
                
                    <div className="column">
                    <div className="card">
                        <img src={require("./images/mateus.png")} className="card" alt="Jane"/>
                        <div className="container">
                        <h2>Mateus Pereira</h2>
                        <p className="title">Responsável pelo Frontend</p>
                        <p>Aluno do 2º ano de LEI</p>
                        <p>https://github.com/Cavalex</p>
                        <p><button className="button" onClick={()=> window.open('https://github.com/Cavalex')}>Contact</button></p>
                        <br></br>
                        </div>
                    </div>
                    </div>

                    <div className="column">
                        <div className="card">
                            <img src={require("./images/toldy.jpg")} className="card" alt="Jane"/>
                        <div className="container">
                            <h2>Francisco Toldy</h2>
                            <p className="title">Responsável pelo Frontend</p>
                            <p>Aluno do 3º ano de LEI</p>
                            <p>https://github.com/ftoldy1415</p>
                            <p><button className="button" onClick={()=> window.open('https://github.com/ftoldy1415')}>Contact</button></p>
                            <br></br>
                        </div>
                        </div>
                    </div>
                </div>

            </body>
        </div>
    );
}

export default AboutUs