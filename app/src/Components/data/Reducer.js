import Cookie from 'js-cookie';

const Reducer = (state, action) => {
  switch (action.type) {
    case 'LOGIN':
      Cookie.set('token', action.payload.session);
      Cookie.set('admin', action.payload.is_admin === 'true' ? 'true' : '');
      return {
        ...state,
        token: action.payload.session,
        admin: action.payload.is_admin === 'true' ? 'true' : '',
      };
    case 'LOGOUT':
      Cookie.set('token', '');
      Cookie.set('admin', '');
      return {
        ...state,
        token: null,
      };
    default:
      return state;
  }
};

export default Reducer;
