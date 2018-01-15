import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import AppContext from './domain/AppContext';
import ContentsContent from './domain/stores/ContentsContent';

const elem = document.getElementById('root');
const env = elem.getAttribute('env');

const context = new AppContext(env);
const props = {
  context: context,
  contents: new ContentsContent(context)
}

ReactDOM.render(
  <App {...props} />,
  elem
);
