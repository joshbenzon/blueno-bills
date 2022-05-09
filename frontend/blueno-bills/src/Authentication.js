import "./App.css";
import { googleSignIn } from "./Firebase";

// import Home from "./navigation-bar/web-pages/home";

// import { useHistory } from "react-router-dom";
// const history = useHistory();

// async function handleSubmit(event) {
//   event.preventDefault();

//   try {
//     await Auth.signIn(email, password);
//     userHasAuthenticated(true);
//     history.push("/");
//   } catch (e) {
//     alert(e.message);
//   }
// }

function Authentication() {
  return (
    <div className="App">
      <button onClick={googleSignIn}> Sign In with Google</button>
      <h1>{localStorage.getItem("name")}</h1>
      <h1>{localStorage.getItem("gmail")}</h1>
      {/* <button onClick={googleSignIn}> Sign In with Google</button> */}
    </div>
  );
}

export default Authentication;
