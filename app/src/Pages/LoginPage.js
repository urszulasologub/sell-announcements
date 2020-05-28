import React from 'react';
import styled from 'styled-components';
import { useForm } from 'react-hook-form';
import { Link } from 'react-router-dom';
import * as yup from 'yup';
import MaterialLink from '@material-ui/core/Link';
import AuthTemplate from 'templates/AuthTemplate';
import TextField from '@material-ui/core/TextField';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';

const LoginPage = () => {
  const classes = useStyles();

  let loginSchema = yup.object().shape({
    email: yup
      .string()
      .email()
      .required(),
    password: yup
      .string()
      .min(5)
      .required(),
  });

  const { register, handleSubmit, errors } = useForm({
    validationSchema: loginSchema,
  });

  const onSubmit = values => {
    console.log(values);
  };

  return (
    <AuthTemplate>
      <h1>sign in</h1>
      <StyledForm className={classes.root} onSubmit={handleSubmit(onSubmit)}>
        <TextField
          name="email"
          inputRef={register}
          label="email"
          fullWidth
          error={errors.email ? true : false}
          helperText={errors.email ? errors.email.message : ''}
        />
        <TextField
          name="password"
          inputRef={register}
          label="password"
          fullWidth
          error={errors.password ? true : false}
          helperText={errors.password ? errors.password.message : ''}
        />
        <StyledButton variant="contained" type="submit">
          sign in
        </StyledButton>
        <StyledMaterialLink component={Link} to="/register">
          Dou do not have an account?
        </StyledMaterialLink>
      </StyledForm>
    </AuthTemplate>
  );
};

export default LoginPage;

const useStyles = makeStyles(theme => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
    },
  },
}));

const StyledForm = styled.form`
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 80%;
  margin: 0 0 30px;
`;

const StyledButton = styled(Button)`
  width: 300px;
`;

const StyledMaterialLink = styled(MaterialLink)`
  font-size: 1rem;
`;
