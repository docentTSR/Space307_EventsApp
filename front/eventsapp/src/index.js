import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import firebase from 'firebase/app';
import 'firebase/firestore';

const firebaseConfig = {
    apiKey: "AIzaSyD5sLTth5pLs45YQfhHXktRgLd3nDcUFkU",
    authDomain: "space307-eventsapp.firebaseapp.com",
    databaseURL: "https://space307-eventsapp.firebaseio.com",
    projectId: "space307-eventsapp",
    storageBucket: "space307-eventsapp.appspot.com",
    messagingSenderId: "1068331209918",
    appId: "1:1068331209918:web:bca43ed0db837ca102faef"
};
firebase.initializeApp(firebaseConfig);
const db = firebase.firestore();
db.collection("events")
    /*.where('repeatable', '==', false).*/.get().then((querySnapshot) => {
    querySnapshot.forEach((doc) => {
        console.log(`${doc.id} => ${JSON.stringify(doc.data())}`);
    });
});

ReactDOM.render(<App />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
