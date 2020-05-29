import Cookie from 'js-cookie';

const Reducer = (state, action) => {
  switch (action.type) {
    case 'LOGIN':
      Cookie.set('token', action.payload);
      return {
        ...state,
        token: action.payload,
      };
    case 'LOGOUT':
      Cookie.set('token', '');
      return {
        ...state,
        token: null,
      };
    default:
      return state;
  }
};

export default Reducer;
