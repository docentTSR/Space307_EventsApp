import React from "react";
import {Row} from "./Row";

export default class ActivitiesList extends React.Component {

    addToList = id => () => {
        this.props.addToList(id);
    };

    removeFromMyList = id => () => {
        this.props.removeFromMyList(id);
    };

    render() {
        return this.props.items.map(item => (
            <div style={{padding: 30, flex: 0.3}} key={item.id}>
                <Row item={item}
                     onAdd={this.addToList(item.id)}
                     myEvents={this.props.myEvents}
                     onRemove={this.removeFromMyList(item.id)}
                />
            </div>
        ))
    }
}
