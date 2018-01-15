import React, { Component } from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import Contents from './containers/Contents';
import Header from './containers/Header';

class App extends Component {
  render() {
    const props = this.props;
    const routes = (
      <BrowserRouter>
        <div>
          <Route exact path='/ui' render={() => <Contents {...props} />} />
        </div>
      </BrowserRouter>
    );

    return (
      <div className="container">
        <Header {...props} />
        {routes}
      </div>
    );
  }
}

export default App;
