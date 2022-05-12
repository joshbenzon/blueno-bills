import React from "react";

import { googleSignIn } from "./authentication";

const Login = () => {
  return (
    <React.Fragment>
      <div className="title-page">
        <div>
          <img
            className="login-pic"
            src={require("../images/blueno-logo.png")}
            alt="blueno bills logo"
          />
        </div>

        <div className={"page-info"}>
          <h1 className="login-title">Blueno Bills</h1>
        </div>

        <div className={"page-buttons"}>
          <div>
            <button className="login-button" onClick={googleSignIn}>
              Login
            </button>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Login;
