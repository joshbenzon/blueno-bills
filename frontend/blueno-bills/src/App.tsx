import React from 'react';
import './App.css';

import Navbar from './navigation-bar/components/Navbar'
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';

import Account from './navigation-bar/web-pages/account';
import Home from './navigation-bar/web-pages/home';
import Menu from './navigation-bar/web-pages/menu';
import Request from './navigation-bar/web-pages/request';
import Settings from './navigation-bar/web-pages/settings';
import SignIn from './navigation-bar/web-pages/signin';
import Transfer from './navigation-bar/web-pages/transfer';

function App() {
    return (
        <Router>
            <Navbar/>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/account" element={<Account/>}/>
                <Route path="/menu" element={<Menu/>}/>
                <Route path="/request" element={<Request/>}/>
                <Route path="/settings" element={<Settings />} />
                <Route path="/signin" element={<SignIn />} />
                <Route path="/transfer" element={<Transfer/>}/>
            </Routes>
        </Router>
    );
}

export default App;
