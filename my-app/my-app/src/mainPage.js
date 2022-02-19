import React from 'react';
import './App.css';


function mainPage(){
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
        <table>
            <thead>
                <tr>
                    <th>Course</th>
                    <th>Shift</th>
                    <th>Status</th>
                    <th>Settings</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><a href="https://www.uminho.pt/PT/ensino/oferta-educativa/_layouts/15/UMinho.PortalUM.UI/Pages/CatalogoCursoDetail.aspx?itemId=4079&catId=12">Chair1__😀 😃 😄 😁 😆 😅 😂 🤣 🥲 ☺️ 😊 😇 🙂 🙃</a></td>
                    <td>PL1</td>
                    <td><p className="status status-waiting">Waiting</p></td>
                    <td>Ahhh</td>
                </tr>

                <tr>
                    <td><a href="https://www.uminho.pt/PT/ensino/oferta-educativa/_layouts/15/UMinho.PortalUM.UI/Pages/CatalogoCursoDetail.aspx?itemId=4079&catId=12">Chair2( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)( ͡° ͜ʖ ͡°)</a></td>
                    <td>PL1</td>
                    <td><p className="status status-enrolled">Enrolled</p></td>
                    <td>Ahhh</td>
                </tr>

                <tr>
                    <td><a href="https://www.uminho.pt/PT/ensino/oferta-educativa/_layouts/15/UMinho.PortalUM.UI/Pages/CatalogoCursoDetail.aspx?itemId=4079&catId=12">Chair3333333333333333333333333333333333333333333333333333333333333333333333333</a></td>
                    <td>PL1</td>
                    <td><p className="status status-waiting">Waiting</p></td>
                    <td>Ahhh</td>
                </tr>

                <tr>
                    <td><a href="https://www.uminho.pt/PT/ensino/oferta-educativa/_layouts/15/UMinho.PortalUM.UI/Pages/CatalogoCursoDetail.aspx?itemId=4079&catId=12">Chair4Chair4Chair4Chair4Chair4Chair4Chair4Chair4Chair4Chair4Chair4Chair4Chair4Chair4Chair4Chair4Chair4Chair4Chair4</a></td>
                    <td>PL1</td>
                    <td><p className="status status-unmatched">No Matches</p></td>
                    <td>Ahhh</td>
                </tr>

            </tbody>
        </table>
    </div>

</div>

    );
}

export default mainPage;