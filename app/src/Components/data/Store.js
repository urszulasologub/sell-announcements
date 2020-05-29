import React, { createContext, useReducer } from 'react';
import Cookie from 'js-cookie';
import Reducer from './Reducer';

const initialState = {
  token: Cookie.get('token') ? Cookie.get('token') : '',
};

const Store = ({ children }) => {
  const [state, dispatch] = useReducer(Reducer, initialState);
  return <Context.Provider value={[state, dispatch]}>{children}</Context.Provider>;
};

export const Context = createContext(initialState);
export default Store;
