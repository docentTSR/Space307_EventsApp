import React from 'react';
import './App.css';
import ActivitiesListContainer from "./containers/ActivitiesListContainter";

class App extends React.Component {
  render() {
    return <div className="App">
      <header className="App-header">
        <div>
          Space307 & DataDuck Events

          <ActivitiesListContainer/>
        </div>
      </header>
    </div>
  }
}

export default App;
