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

import { useState } from "react";
import { useEffect } from "react";

interface Row {
  StudentID: string;
  email: string;
  mealSwipes: string;
  flexPoints: string;
  bearBucks: string;
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
    const [rows, setRows] = useState<Row[] |null>(null);

    function setDatabase(db: database): void {
        console.log("enters set db")
        setTableName(db["name"]);
        setTableHeaders(db["headers"]);
        setRows(db["rows"]);
    }

    function loadDatabase(): void {
        console.log("enters load database")


        fetch("http://localhost:4567/table", {
            method: 'GET',
        })
            .then(r => r.json())
            .then((db: database) => setDatabase(db))
        console.log("database loaded")

    }

    useEffect(() => {
        loadDatabase()

        console.log("table name: " + tableName)
        console.log("table headers: " + tableHeaders)
        console.log("table values: " + rows)

      }, [])
     
      //creating a dummy user until we have auth established! 

    const currUserEmail = "Jillian_Dominguez@brown.edu"; //hard coding for now until we have auth established and can store curr user data
    let currUserMealSwipes: number = 0;
    let currUserFlexPoints: number = 0;
    let currUserBearBucks: number = 0;
    if (rows) {
        for (let i = 0; i < rows.length; i++) {
        if (equalsIgnoringCase(rows[i].email, currUserEmail)) {
            //user
            currUserMealSwipes = parseInt(rows[i].mealSwipes);
            currUserFlexPoints = parseInt(rows[i].flexPoints);
            currUserBearBucks = parseInt(rows[i].bearBucks);
        }
        
        }
    }

    //from here: https://stackoverflow.com/questions/2140627/how-to-do-case-insensitive-string-comparison
  function equalsIgnoringCase(text: string, other: string) {
    return text.localeCompare(other, undefined, { sensitivity: "base" }) === 0;
  }


    return (
        <Router>
            <Navbar/>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/request" element={<Request/>}/>

                <Route path="/settings" element={<Settings/>} />

                <Route path="/transfer" element={<Transfer/>}/>
                <Route path="/menu" element={<Menu/>}/>
                <Route path="/account" element={<Account tableHeaders = {tableHeaders} userEmail = {currUserEmail} userMS = {currUserMealSwipes} userFP = {currUserFlexPoints} userBB = {currUserBearBucks} />}/>
                <Route path="/settings" element={<Settings/>}/>
                <Route path="/logout" element={<LogOut/>}/>

                <Route path="/requestMeal" element={<RequestMeal tableHeaders = {tableHeaders} rows = {rows}/>}/>
                <Route path="/requestFlex" element={<RequestFlex tableHeaders = {tableHeaders} rows = {rows}/>}/>
                <Route path="/requestBear" element={<RequestBear tableHeaders = {tableHeaders} rows = {rows}/>}/>


                <Route path="/transferMeal" element={<TransferMeal tableHeaders = {tableHeaders} rows = {rows} loadDatabase={() => loadDatabase()} userEmail = {currUserEmail} userMS = {currUserMealSwipes} />}/>
                <Route path="/transferFlex" element={<TransferFlex tableHeaders = {tableHeaders} rows = {rows} userEmail = {currUserEmail} userFP = {currUserFlexPoints} />}/>
                <Route path="/transferBear" element={<TransferBear tableHeaders = {tableHeaders} rows = {rows} userEmail = {currUserEmail} userBB = {currUserBearBucks}/>}/>
            </Routes>
        </Router>
    );

}

export default App;
