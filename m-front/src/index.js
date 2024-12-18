import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import AutPage from './aut.jsx';
import StartPage from './start.jsx';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import InfoPage from './info.jsx';
import UserPage from './userPage.jsx';
import HotelsPage from './hotels.jsx';
import RegPage from './reg.jsx';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  // <BrowserRouter>
  //  <Routes>
  //  <Route path="/" element={<AutPage />}/>
  //  <Route path= "/start" element={<StartPage/>}></Route>
  //  <Route path= "/info" element={<InfoPage/>}></Route>
  //  <Route path= "/bookings" element={<UserPage/>}></Route>
  //  <Route path= "/hotels" element={<HotelsPage/>}></Route>
  //  <Route path= "/register" element={<RegPage/>}></Route>
  //  </Routes>
  // </BrowserRouter>
  <BrowserRouter>
            <Routes>
                <Route path="/" element={<AutPage />} />
                <Route path="/start/:id" element={<StartPage />} /> {/* Изменено для передачи id */}
                <Route path="/info/:id" element={<InfoPage />} />
                <Route path="/bookings/:id" element={<UserPage />} />
                <Route path="/hotels/:id" element={<HotelsPage />} />
                <Route path="/register" element={<RegPage />} />
            </Routes>
        </BrowserRouter>
);


reportWebVitals();
