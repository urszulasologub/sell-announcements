import React from 'react';
import styled from 'styled-components';
import AuthTemplate from 'templates/AuthTemplate';
import TextField from '@material-ui/core/TextField';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';

const useStyles = makeStyles(theme => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
    },
  },
}));

const StyledForm = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 80%;
`;

const StyledTextField = styled(TextField)`
  width: 300px;
`;

const StyledButton = styled(Button)`
  width: 300px;
`;

const LoginPage = () => {
  const classes = useStyles();
  return (
    <AuthTemplate>
      <h1>sign in</h1>
      <StyledForm className={classes.root}>
        <StyledTextField id="standard-basic" label="email" type="email" />
        <StyledTextField id="standard-basic" label="password" type="password" />
        <StyledButton variant="contained">sign in</StyledButton>
      </StyledForm>
    </AuthTemplate>
  );
};

export default LoginPage;
