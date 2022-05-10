import React from "react";
import "../../App.css";

import Navbar from "../components/Navbar";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Home from "./home";
import Request from "./request";
import Transfer from "./transfer";
import Menu from "./menu";
import Account from "./account";
import Login from "./login";

import RequestMeal from "./requestMeal";
import RequestFlex from "./requestFlex";
import RequestBear from "./requestBear";

import TransferMeal from "./transferMeal";
import TransferFlex from "./transferFlex";
import TransferBear from "./transferBear";

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

function Main() {
  const [tableName, setTableName] = useState<string | null>(null);
  const [tableHeaders, setTableHeaders] = useState<string[] | null>(null);
  const [rows, setRows] = useState<Row[] | null>(null);

  function setDatabase(db: database): void {
    console.log("enters set db");

    setTableName(db["name"]);
    setTableHeaders(db["headers"]);
    setRows(db["rows"]);
  }

  function loadDatabase(): void {
    console.log("enters load database");

    fetch("http://localhost:4567/table", {
      method: "GET",
    })
      .then((r) => r.json())
      .then((db: database) => setDatabase(db));
  }

  useEffect(() => {
    loadDatabase();

    console.log("table name: " + tableName);
    console.log("table headers: " + tableHeaders);
    console.log("table values: " + rows);
  }, []);

  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/home" element={<Home />} />
      <Route path="/request" element={<Request />} />
      <Route path="/transfer" element={<Transfer />} />
      <Route path="/menu" element={<Menu />} />
      <Route
        path="/account"
        element={<Account tableHeaders={tableHeaders} rows={rows} />}
      />

      <Route
        path="/requestMeal"
        element={<RequestMeal tableHeaders={tableHeaders} rows={rows} />}
      />
      <Route
        path="/requestFlex"
        element={<RequestFlex tableHeaders={tableHeaders} rows={rows} />}
      />
      <Route
        path="/requestBear"
        element={<RequestBear tableHeaders={tableHeaders} rows={rows} />}
      />

      <Route
        path="/transferMeal"
        element={<TransferMeal tableHeaders={tableHeaders} rows={rows} />}
      />
      <Route
        path="/transferFlex"
        element={<TransferFlex tableHeaders={tableHeaders} rows={rows} />}
      />
      <Route
        path="/transferBear"
        element={<TransferBear tableHeaders={tableHeaders} rows={rows} />}
      />
    </Routes>
  );
}

export default Main;
