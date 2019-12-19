import React from "react";
import {Row} from "./Row";

export default class ActivitiesList extends React.Component {
    render() {
        return this.props.items.map(item => (
            <div style={{padding: 30, flex: 0.3}}>
                <Row item={item}/>
            </div>
        ))
    }
}
