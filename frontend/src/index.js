import "materialize-css/dist/css/materialize.min.css";
import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Switch, Route, useParams } from "react-router-dom";
import './index.css';
import App from './App';
import Follow from './Follow';

const user = localStorage.getItem('user');

function FollowRoute() {
  let { id } = useParams();
  return <Follow id={id} />;
}

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <Switch>
        <Route path="/" exact>
          <App user={user} />
        </Route>
        <Route path="/:id">
          <FollowRoute />
        </Route>
      </Switch>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);
