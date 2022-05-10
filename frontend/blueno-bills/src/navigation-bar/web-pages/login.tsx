import React from "react";

import { googleSignIn } from "./authentication";

const Login = () => {
  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Blueno Bills</h1>
      </div>

      <div className={"page-buttons"}>
        <div className="log-out-button">
          <button onClick={googleSignIn}>Login!</button>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Login;
