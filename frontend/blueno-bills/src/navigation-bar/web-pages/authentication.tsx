import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getAuth, GoogleAuthProvider, signInWithPopup } from "firebase/auth";

import updateUserList from "./home";

// add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries
// for Firebase JS SDK v7.20.0 and later, measurementId is optional

const firebaseConfig = {
  apiKey: "AIzaSyDcAMSb5HEwUsL90VhH8IADPexu_D0CVcs",
  authDomain: "cs32-final-project-784ef.firebaseapp.com",
  projectId: "cs32-final-project-784ef",
  storageBucket: "cs32-final-project-784ef.appspot.com",
  messagingSenderId: "518203758972",
  appId: "1:518203758972:web:269b6ac00e738a7c8d2cf9",
  measurementId: "G-K52MRFYQ6E",
};

// initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
export const auth = getAuth(app);
const gmailProvider = new GoogleAuthProvider(); // using Gmail as our authentication form

export const googleSignIn = () => {
    // makes a popup window appear where a user will be prompted to sign in with their Gmail
  signInWithPopup(auth, gmailProvider)
    .then((result) => {
      const name = result.user.displayName;
      const gmail = result.user.email;

      // stores the information given to us from the Google auth somewhere
      // on our browser to remember who is logged in
      localStorage.setItem("name", name!);
      localStorage.setItem("gmail", gmail!);

      console.log("GOOGLE SIGN IN");
    })
      // will add user to the list of active Blueno Bill users
    .then(updateUserList)
      // Beacause their emails will be listed for all users to see,
      // we added a privacy policy agreement for added transparency with our users
    .then(() => (window.location.href = "/privacyPolicy"))

    .catch((error) => {
      console.log(error);
    });
};
