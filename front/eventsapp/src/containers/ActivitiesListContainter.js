import { connect } from 'react-redux'
import ActivitiesList from '../components/ActivitiesList'
import {bindActionCreators} from "redux";
import {addToMyList, removeFromMyList} from "../actions/itemsActions";

const mapStateToProps = (state) => ({
   items: state.items,
   myEvents: state.myEvents,
});

const mapDispatchToProps = (dispatch) => ({
   addToList: bindActionCreators(addToMyList, dispatch),
   removeFromMyList: bindActionCreators(removeFromMyList, dispatch),
});

const ActivitiesListContainer = connect(
    mapStateToProps,
    mapDispatchToProps,
)(ActivitiesList);

export default ActivitiesListContainer
