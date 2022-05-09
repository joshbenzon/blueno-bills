import "./App.css";
import {googleSignIn} from "./Firebase";

function App() {
    return (
        <div className= "App">
            <button onClick={googleSignIn}> Sign In With Google</button>
            <h1>{localStorage.getItem("name")}</h1>
            <h1>{localStorage.getItem("gmail")}</h1>
        </div>
    );
}

export default App;
