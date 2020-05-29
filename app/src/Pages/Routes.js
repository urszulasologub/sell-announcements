import React, { useContext } from 'react';
import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';
import HomePage from 'pages/HomePage';
import LoginPage from 'pages/LoginPage';
import RegisterPage from 'pages/RegisterPage';
import AnnouncementCreatePage from 'pages/AnnouncementCreatePage';
import { Context } from 'components/data/Store';

const Routes = () => {
  const [state] = useContext(Context);
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={HomePage} />
        <Route exact path="/login" component={LoginPage} />
        <Route exact path="/register" component={RegisterPage} />
        {state.token ? (
          <>
            <Route exact path="/announcement/create" component={AnnouncementCreatePage} />
          </>
        ) : null}
        <Redirect to="/" />
      </Switch>
    </BrowserRouter>
  );
};

export default Routes;
