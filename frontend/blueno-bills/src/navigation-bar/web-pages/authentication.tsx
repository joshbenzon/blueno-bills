import { useState } from "react";

import { googleSignIn } from "./firebase";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

interface AuthProps {
  email: string;
  authenticated: boolean;
}

function Authentication() {
  console.log("AUTHENTICATION");

  const [inputEmail, setInputEmail] = useState<string | null>("");
  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(false);

  // setInputEmail(email);
  // setIsAuthenticated(true);

  return (
    <div className="auth-page">
      <button onClick={googleSignIn}> Sign In with Google</button>
      <h1>{localStorage.getItem("name")}</h1>
      <h1>{localStorage.getItem("gmail")}</h1>
    </div>

    //   <Routes>
    //     <Route path="/login" element={<First />}></Route>
    //   </Routes>
    // </Router>
  );
}

export default Authentication;
