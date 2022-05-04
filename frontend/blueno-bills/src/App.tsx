import React from "react";
import "./App.css";

import Navbar from "./navigation-bar/components/Navbar";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Account from "./navigation-bar/web-pages/account";
import Home from "./navigation-bar/web-pages/home";
import Menu from "./navigation-bar/web-pages/menu";
import Request from "./navigation-bar/web-pages/request";
import Settings from "./navigation-bar/web-pages/settings";
import LogOut from "./navigation-bar/web-pages/logout";
import Transfer from "./navigation-bar/web-pages/transfer";

import RequestMeal from "./navigation-bar/web-pages/requestMeal";
import RequestFlex from "./navigation-bar/web-pages/requestFlex";
import RequestBear from "./navigation-bar/web-pages/requestBear";

import TransferMeal from "./navigation-bar/web-pages/transferMeal";
import TransferFlex from "./navigation-bar/web-pages/transferFlex";
import TransferBear from "./navigation-bar/web-pages/transferBear";

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/account" element={<Account />} />
        <Route path="/menu" element={<Menu />} />
        <Route path="/request" element={<Request />} />
        <Route path="/settings" element={<Settings />} />
        <Route path="/logout" element={<LogOut />} />
        <Route path="/transfer" element={<Transfer />} />

        <Route path="/requestMeal" element={<RequestMeal />} />
        <Route path="/requestFlex" element={<RequestFlex />} />
        <Route path="/requestBear" element={<RequestBear />} />

        <Route path="/transferMeal" element={<TransferMeal />} />
        <Route path="/transferFlex" element={<TransferFlex />} />
        <Route path="/transferBear" element={<TransferBear />} />
      </Routes>
    </Router>
  );
}

export default App;
