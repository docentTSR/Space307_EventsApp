import {db} from "../index";

export const TRY_LOAD_LIST = 'TRY_LOAD_LIST';
export const LOAD_LIST_SUCCESS = 'LOAD_LIST_SUCCESS';
export const LOAD_LIST_FAILURE = 'LOAD_LIST_FAILURE';

const tryLoadList = () => ({
    type: 'TRY_LOAD_LIST',
});

const loadListSuccessfull = (data) => ({
    type: 'LOAD_LIST_SUCCESS',
    payload: data,
});

const loadListFailure = (error) => ({
    type: 'LOAD_LIST_FAILURE',
    payload: error,
});

export const loadItems = dispatch => {
    dispatch(tryLoadList);
    db.collection("events").get().then((querySnapshot) => {
        let data = [];
        querySnapshot.forEach((doc) => {
            data.push(doc.data())
        });
        dispatch(loadListSuccessfull(data));
    }).catch(err => dispatch(loadListFailure(err)));
};
