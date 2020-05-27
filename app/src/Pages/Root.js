import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import GlobalStyle from 'theme/GlobalStyle';
import HomePage from 'pages/HomePage';
import LoginPage from 'pages/LoginPage';

const Root = () => {
  return (
    <>
      <GlobalStyle />
      <BrowserRouter>
        <Switch>
          <Route exact path="/" component={HomePage} />
          <Route exact path="/login" component={LoginPage} />
        </Switch>
      </BrowserRouter>
    </>
  );
};

export default Root;
