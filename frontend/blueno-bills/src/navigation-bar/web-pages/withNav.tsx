import React from "react";
import NavBar from "../components/Navbar";
import { Outlet } from "react-router";

export default () => {
  return (
    <>
      <NavBar />
      <Outlet />
    </>
  );
};
