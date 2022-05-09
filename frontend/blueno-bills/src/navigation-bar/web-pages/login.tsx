import React from "react";

import { googleSignIn } from "./firebase";
import Authentication from "./authentication";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

const Login = () => {
  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Login Page!</h1>
      </div>

      <div className={"page-buttons"}>
        <div className="log-out-button">
          <button onClick={() => (window.location.href = "/home")}>
            Login!
          </button>
        </div>
      </div>

      {/* <Routes>
        <Route path="/" element={<Authentication />}></Route>
      </Routes> */}
    </React.Fragment>
  );
};

export default Login;
