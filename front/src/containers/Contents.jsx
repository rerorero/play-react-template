import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Route, Link } from 'react-router-dom';

class Contents extends Component {
  componentDidMount() {
    this.props.contents.say();
  }

  render() {
    const { contents } = this.props;
    return (
      <div>
        <p>{ contents.content }</p>
      </div>
    );
  }
}

export default observer(Contents);
