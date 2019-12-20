import {ADD_TO_MY_LIST, REMOVE_FROM_MY_LIST} from "../actions/itemsActions";

const initialState = [];

export function myEventsReducer(state = initialState, action) {
    switch (action.type) {
        case ADD_TO_MY_LIST:
            if (state.includes(+action.payload.id)) {
                return state;
            }
            return [...state, +action.payload.id];
        case REMOVE_FROM_MY_LIST:
            return state.filter(item => item !== (+action.payload.id));
        default:
            return state
    }
}
