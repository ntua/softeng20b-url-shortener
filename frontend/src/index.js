import "materialize-css/dist/css/materialize.min.css";
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';

const user = localStorage.getItem('user');

ReactDOM.render(
  <React.StrictMode>
    <App user={user} />
  </React.StrictMode>,
  document.getElementById('root')
);
