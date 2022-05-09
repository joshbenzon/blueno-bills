import "./App.css";
import {googleSignIn} from "./Firebase";

function App() {
    return (
        <div className= "App">
            <button onClick={googleSignIn}> Sign In With Google</button>
        </div>
    );
}

export default App;
