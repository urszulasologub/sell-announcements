import React, { useContext } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { Link, useHistory } from 'react-router-dom';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import { Context } from 'components/data/Store';

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
  },
  appBar: {
    background: '#126e69',
  },
  title: {
    flexGrow: 1,
  },
}));

const Navbar = () => {
  const classes = useStyles();
  const [state, dispatch] = useContext(Context);
  const history = useHistory();

  return (
    <div className={classes.root}>
      <AppBar position="static" className={classes.appBar}>
        <Toolbar>
          <Typography
            variant="h6"
            className={classes.title}
            onClick={() => {
              history.push('/');
            }}
          >
            SellANN
          </Typography>
          {state.token ? (
            <>
              {state.admin ? (
                <Button
                  color="inherit"
                  onClick={() => {
                    // history.push('/announcement/create');
                    console.log('admin panel');
                  }}
                >
                  Admin Panel
                </Button>
              ) : null}
              <Button
                color="inherit"
                onClick={() => {
                  history.push('/announcement/create');
                }}
              >
                Add Announcement
              </Button>
              <Button
                color="inherit"
                onClick={() => {
                  dispatch({ type: 'LOGOUT' });
                }}
              >
                Logout
              </Button>
            </>
          ) : (
            <Button color="inherit" component={Link} to="/login">
              Login
            </Button>
          )}
        </Toolbar>
      </AppBar>
    </div>
  );
};

export default Navbar;
