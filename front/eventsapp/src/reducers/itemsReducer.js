const initialState = [];

export function itemsReducer(state = initialState, action) {
    switch (action.type) {
        case 'LOAD_LIST_SUCCESS':
            console.log([...state, ...action.payload]);
            return [...state, ...action.payload];
        default:
            return state
    }
}
