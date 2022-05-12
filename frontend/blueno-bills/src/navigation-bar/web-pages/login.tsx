import React from "react";

import { googleSignIn } from "./authentication";

// This is what the user will first see when they open our application
// They will be prompted to log in via Google to continue using our services
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
            // This is an on demand click button that will open our pop up window to Google
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
