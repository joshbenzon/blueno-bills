// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import {getAuth, GoogleAuthProvider, signInWithPopup} from 'firebase/auth';
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyDcAMSb5HEwUsL90VhH8IADPexu_D0CVcs",
    authDomain: "cs32-final-project-784ef.firebaseapp.com",
    projectId: "cs32-final-project-784ef",
    storageBucket: "cs32-final-project-784ef.appspot.com",
    messagingSenderId: "518203758972",
    appId: "1:518203758972:web:269b6ac00e738a7c8d2cf9",
    measurementId: "G-K52MRFYQ6E"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
export const auth = getAuth(app);
const gmailProvider = new GoogleAuthProvider();
export const googleSignIn = () => {
    signInWithPopup(auth, gmailProvider)
        .then((result) => {
            const name = result.user.displayName;
            const gmail = result.user.email;

            // stores the information given to us from the Google auth somewhere
            // on our browser to remember who is logged in
            localStorage.setItem("name", name);
            localStorage.setItem("gmail", gmail);
        })
        .catch((error) => {
            console.log(error);
        });
};

