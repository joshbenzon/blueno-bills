import React from "react";
import "./App.css";

import { useState } from "react";
import { useEffect } from "react";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import WithNav from "./navigation-bar/web-pages/withNav";
import Main from "./navigation-bar/web-pages/main";

import WithoutNav from "./navigation-bar/web-pages/withoutNav";
import Login from "./navigation-bar/web-pages/login";

import Authentication from "./navigation-bar/web-pages/authentication";

import Home from "./navigation-bar/web-pages/home";
import Request from "./navigation-bar/web-pages/request";
import Transfer from "./navigation-bar/web-pages/transfer";
import Menu from "./navigation-bar/web-pages/menu";
import Account from "./navigation-bar/web-pages/account";
// import Login from "./navigation-bar/web-pages/login";

import RequestMeal from "./navigation-bar/web-pages/requestMeal";
import RequestFlex from "./navigation-bar/web-pages/requestFlex";
import RequestBear from "./navigation-bar/web-pages/requestBear";

import TransferMeal from "./navigation-bar/web-pages/transferMeal";
import TransferFlex from "./navigation-bar/web-pages/transferFlex";
import TransferBear from "./navigation-bar/web-pages/transferBear";

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

interface userStatus {
  isLogin: boolean | null;
}

function App() {
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

  //   const [isLogIn, setIsLogIn] = useState<boolean | null>(false);

  //   return (
  //     <Router>
  //       <Routes>
  //         <Route path="/" element={<Main />} />
  //         <Route path="/login" element={<Main />} />
  //       </Routes>
  //     </Router>
  //   );

  //   const [isLogin, setIsLogin] = useState<boolean | null>(false);

  //   function setStatusTrue() {
  //     setIsLogin(true);
  //   }

  //   function setStatusFalse() {
  //     setIsLogin(false);
  //   }

  //   let pageStatus;

  //   if (!isLogin) {
  //     pageStatus = <Main></Main>;
  //     // setStatusTrue();
  //   } else {
  //     pageStatus = <Login></Login>;
  //     // setStatusFalse();
  //   }

  //   return <React.Fragment>{pageStatus}</React.Fragment>;

  return (
    <Router>
      <Routes>
        <Route element={<WithoutNav />}>
          <Route path="/" element={<Login />} />
        </Route>

        <Route element={<WithNav />}>
          <Route path="/main/*" element={<Main />} />

          {/* <Route path="/" element={<Login />} /> */}
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
        </Route>
      </Routes>
    </Router>
  );

  //   return <React.Fragment></React.Fragment>;
}

// function handleLoginClick() {
//   this.setState({ isLoggedIn: true });
// }

// function handleLogoutClick() {
//   this.setState({ isLoggedIn: false });
// }

export default App;
