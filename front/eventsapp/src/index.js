import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import firebase from 'firebase/app';
import 'firebase/firestore';
import { Provider } from "react-redux";
import {applyMiddleware, combineReducers, createStore} from 'redux'
import {itemsReducer} from "./reducers/itemsReducer";
import thunk from "redux-thunk";
import logger from 'redux-logger'
import {loadItems} from "./actions/itemsActions";

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
export { db };

const AppReducer = combineReducers({
    items: itemsReducer,
});

const middlewares = [thunk];

const DEV = true;

if (DEV) {
    middlewares.push(logger)
}

const store = createStore(AppReducer, applyMiddleware(...middlewares));

loadItems(store.dispatch);

class AppComponent extends React.Component {
    render() {
        return (
            <Provider store={store}>
                <App />
            </Provider>
        )
    }
}

ReactDOM.render(<AppComponent />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
