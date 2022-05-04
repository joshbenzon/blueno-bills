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

import RequestMeal from "./navigation-bar/web-pages/requestMeal";
import RequestFlex from "./navigation-bar/web-pages/requestFlex";
import RequestBear from "./navigation-bar/web-pages/requestBear";

import TransferMeal from "./navigation-bar/web-pages/transferMeal";
import TransferFlex from "./navigation-bar/web-pages/transferFlex";
import TransferBear from "./navigation-bar/web-pages/transferBear";
import { useState } from 'react';
import { useEffect } from 'react';

interface Row{
    StudentID: string;
    email:string;
    mealSwipes:string;
    flexPoints:string;
    bearBucks:string;
}

interface database {
    // each table is a slot in the array
    name: string;
    // each table is a slot in the outer array, each header for a table is a slot in the inner array
    headers: string[];
    // each table is a slot in the outer array, each header for a table is a slot in the inner array, each value for a
    // header is a slot in the inner-inner array
    rows: Row[];
}

function App() {
    const [tableName, setTableName] = useState<string | null>(null);
    const [tableHeaders, setTableHeaders] = useState<string[] |null>(null);
    const [rows, setRows] = useState<string[][] |null>(null);

    function setDatabase(db: database): void {
        console.log("enters set db")
        setTableName(db["name"]);
        setTableHeaders(db["headers"]);
        let arr = Array.from(db["rows"].values())
        let row:string[] = []
        let rows:string[][] = [[]]
        for(let i =0; i<arr.length; i++){
            row.push(arr[i].StudentID);
            row.push(arr[i].email)
            row.push(arr[i].mealSwipes)
            row.push(arr[i].flexPoints)
            row.push(arr[i].bearBucks)
            console.log("row: " + row)
            rows.push(row)
            row = []
        }
        setRows(rows);
    }

    function loadDatabase(): void {
        console.log("enters load database")
        fetch("http://localhost:4567/table", {
            method: 'GET',
        }).then(r => r.json()).then((db: database) => setDatabase(db))
    }

    useEffect(() => {
        loadDatabase()
        console.log("table name: " + tableName)
        console.log("table headers: " + tableHeaders)
        console.log("table values: " + rows)
      }, [])
     

    
    return (
        <Router>
            <Navbar/>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/account" element={<Account/>}/>
                <Route path="/menu" element={<Menu/>}/>
                <Route path="/request" element={<Request/>}/>
                <Route path="/settings" element={<Settings/>} />
                <Route path="/signin" element={<SignIn />} />
                <Route path="/transfer" element={<Transfer/>}/>

                <Route path="/requestMeal" element={<RequestMeal/>}/>
                <Route path="/requestFlex" element={<RequestFlex/>}/>
                <Route path="/requestBear" element={<RequestBear/>}/>

                <Route path="/transferMeal" element={<TransferMeal tableHeaders = {tableHeaders} rows = {rows}/>}/>
                <Route path="/transferFlex" element={<TransferFlex/>}/>
                <Route path="/transferBear" element={<TransferBear/>}/>
            </Routes>
        </Router>
    );
}

export default App;
