import React, { Component } from 'react';
import { observer } from 'mobx-react';

class Header extends Component {
  render() {
    const { context } = this.props;
    return (
      <div className="row">
        <div className="col">
          <div className={`p-3 mb-2 ${context.conf.headerColor}`}>
            <h2 className="text-center"> Header({context.conf.env}) </h2>
          </div>
        </div>
      </div>
    );
  }
}

export default observer(Header);

