import React from "react";
import "./App.css";

import { useState } from "react";
import { useEffect } from "react";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import WithNav from "./navigation-bar/web-pages/withNav";
import WithoutNav from "./navigation-bar/web-pages/withoutNav";
import Login from "./navigation-bar/web-pages/login";

import Home from "./navigation-bar/web-pages/home";
import Request from "./navigation-bar/web-pages/request";
import Transfer from "./navigation-bar/web-pages/transfer";
import Menu from "./navigation-bar/web-pages/menu";
import Account from "./navigation-bar/web-pages/account";

import RequestMeal from "./navigation-bar/web-pages/requestMeal";
import RequestFlex from "./navigation-bar/web-pages/requestFlex";
import RequestBear from "./navigation-bar/web-pages/requestBear";

import TransferMeal from "./navigation-bar/web-pages/transferMeal";
import TransferFlex from "./navigation-bar/web-pages/transferFlex";
import TransferBear from "./navigation-bar/web-pages/transferBear";

import RattyMenus from "./navigation-bar/web-pages/rattyMenus";
import VDubMenus from "./navigation-bar/web-pages/vdubMenus";
import AndrewsMenus from "./navigation-bar/web-pages/andrewsMenus";
import IvyRoomMenus from "./navigation-bar/web-pages/ivyRoomMenus";
import BlueRoomMenus from "./navigation-bar/web-pages/blueRoomMenus";
import JosMenus from "./navigation-bar/web-pages/josMenus";

interface Row {
  StudentID: string;
  email: string;
  mealSwipes: string;
  flexPoints: string;
  bearBucks: string;
}

interface database {
  name: string; // each table is a slot in the outer array
  headers: string[]; // each header for a table is a slot in the inner array
  rows: Row[]; // each value for a header is a slot in the inner-inner array
}

function App() {
  // load ratty menus
  const [menuRatty, setMenuRatty] = useState<string[][]>([]);

  function setRatty(menu: string[][]): void {
    menuRatty[0] = menu[0] as string[];
    menuRatty[1] = menu[1] as string[];
    menuRatty[2] = menu[2] as string[];

    setMenuRatty(menuRatty);
  }

  function loadRatty(): void {
    fetch("http://localhost:4567/ratty", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((menu: string[][]) => setRatty(menu));
  }

  // load vdub menus
  const [menuVDub, setMenuVDub] = useState<string[][]>([]);

  function setVDub(menu: string[][]): void {
    menuVDub[0] = menu[0] as string[];
    menuVDub[1] = menu[1] as string[];
    menuVDub[2] = menu[2] as string[];

    setMenuVDub(menuVDub);
  }

  function loadVDub(): void {
    fetch("http://localhost:4567/vdub", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((menu: string[][]) => setVDub(menu));
  }

  // load andrews menus
  const [menuAndrews, setMenuAndrews] = useState<string[][]>([]);

  function setAndrews(menu: string[][]): void {
    menuAndrews[0] = menu[0] as string[];
    menuAndrews[1] = menu[1] as string[];

    setMenuAndrews(menuAndrews);
  }

  function loadAndrews(): void {
    fetch("http://localhost:4567/andrews", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((menu: string[][]) => setAndrews(menu));
  }

  // load ivy room menus
  const [menuIvyRoom, setMenuIvyRoom] = useState<string[][]>([]);

  function setIvyRoom(menu: string[][]): void {
    menuIvyRoom[0] = menu[0] as string[];
    menuIvyRoom[1] = menu[1] as string[];

    setMenuIvyRoom(menuIvyRoom);
  }

  function loadIvyRoom(): void {
    fetch("http://localhost:4567/ivyroom", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((menu: string[][]) => setIvyRoom(menu));
  }

  // load blue room menus
  const [menuBlueRoom, setMenuBlueRoom] = useState<string[][]>([]);

  function setBlueRoom(menu: string[][]): void {
    menuBlueRoom[0] = menu[0] as string[];
    menuBlueRoom[1] = menu[1] as string[];

    setMenuBlueRoom(menuBlueRoom);
  }

  function loadBlueRoom(): void {
    fetch("http://localhost:4567/blueroom", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((menu: string[][]) => setBlueRoom(menu));
  }

  // load jos menus
  const [menuJos, setMenuJos] = useState<string[][]>([]);

  function setJos(menu: string[][]): void {
    menuJos[0] = menu[0] as string[];

    setMenuJos(menuJos);
  }

  function loadJos(): void {
    fetch("http://localhost:4567/jos", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((menu: string[][]) => setJos(menu));
  }

  // load database
  const [tableName, setTableName] = useState<string | null>(null);
  const [tableHeaders, setTableHeaders] = useState<string[] | null>(null);
  const [rows, setRows] = useState<Row[] | null>(null);

  function setDatabase(db: database): void {
    setTableName(db["name"]);
    setTableHeaders(db["headers"]);
    setRows(db["rows"]);
  }

  function loadDatabase(): void {
    fetch("http://localhost:4567/table", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((db: database) => setDatabase(db));
  }

  useEffect(() => {
    loadDatabase();
    loadRatty();
    loadVDub();
    loadAndrews();
    loadIvyRoom();
    loadBlueRoom();
    loadJos();
  }, []);

  const currUserEmail = localStorage.getItem("gmail") as string;

  let currUserMealSwipes: number = 0;
  let currUserFlexPoints: number = 0;
  let currUserBearBucks: number = 0;

  if (rows) {
    for (let i = 0; i < rows.length; i++) {
      if (equalsIgnoringCase(rows[i].email, currUserEmail)) {
        currUserMealSwipes = parseInt(rows[i].mealSwipes);
        currUserFlexPoints = parseInt(rows[i].flexPoints);
        currUserBearBucks = parseInt(rows[i].bearBucks);
      }
    }
  }

  // source: https://stackoverflow.com/questions/2140627/how-to-do-case-insensitive-string-comparison
  function equalsIgnoringCase(text: string, other: string) {
    return text.localeCompare(other, undefined, { sensitivity: "base" }) === 0;
  }

  return (
    <Router>
      <Routes>
        <Route element={<WithoutNav />}>
          <Route path="/" element={<Login />} />
        </Route>

        <Route element={<WithNav />}>
          <Route path="/home" element={<Home />} />
          <Route path="/request" element={<Request />} />
          <Route path="/transfer" element={<Transfer />} />
          <Route path="/menu" element={<Menu />} />
          <Route
            path="/account"
            element={
              <Account
                tableHeaders={tableHeaders}
                userEmail={currUserEmail}
                userMS={currUserMealSwipes}
                userFP={currUserFlexPoints}
                userBB={currUserBearBucks}
              />
            }
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
            element={
              <TransferMeal
                tableHeaders={tableHeaders}
                rows={rows}
                userEmail={currUserEmail}
                userMS={currUserMealSwipes}
              />
            }
          />
          <Route
            path="/transferFlex"
            element={
              <TransferFlex
                tableHeaders={tableHeaders}
                rows={rows}
                userEmail={currUserEmail}
                userFP={currUserFlexPoints}
              />
            }
          />
          <Route
            path="/transferBear"
            element={
              <TransferBear
                tableHeaders={tableHeaders}
                rows={rows}
                userEmail={currUserEmail}
                userBB={currUserBearBucks}
              />
            }
          />
          <Route
            path="/rattyMenus"
            element={<RattyMenus props={menuRatty} />}
          />
          <Route path="/vdubMenus" element={<VDubMenus props={menuVDub} />} />
          <Route
            path="/andrewsMenus"
            element={<AndrewsMenus props={menuAndrews} />}
          />
          <Route
            path="/ivyRoomMenus"
            element={<IvyRoomMenus props={menuIvyRoom} />}
          />
          <Route
            path="/blueRoomMenus"
            element={<BlueRoomMenus props={menuBlueRoom} />}
          />
          <Route path="/josMenus" element={<JosMenus props={menuJos} />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
