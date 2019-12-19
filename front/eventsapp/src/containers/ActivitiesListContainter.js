import { connect } from 'react-redux'
import ActivitiesList from '../components/ActivitiesList'

const mapStateToProps = (state) => ({
   items: state.items,
});

const ActivitiesListContainer = connect(
    mapStateToProps,
)(ActivitiesList);

export default ActivitiesListContainer
