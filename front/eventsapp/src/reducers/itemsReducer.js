import {LOAD_LIST_SUCCESS} from "../actions/itemsActions";
import _ from 'lodash'

const initialState = [];

export function itemsReducer(state = initialState, action) {
    switch (action.type) {
        case LOAD_LIST_SUCCESS:
            const result = [...state, ...action.payload];
            return _.uniqBy(result, item => item.id);
        default:
            return state
    }
}
