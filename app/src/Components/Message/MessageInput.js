import React, { useState, useContext } from 'react';
import axios from 'axios';
import styled from 'styled-components';
import { useForm } from 'react-hook-form';
import TextField from '@material-ui/core/TextField';
import { REMOTE_HOST } from 'config';
import { Context } from 'components/data/Store';
import Button from '@material-ui/core/Button';

const MessageInput = ({ data }) => {
  const { handleSubmit } = useForm();
  const [state] = useContext(Context);

  const onSubmit = ({ message }) => {
    const options = {
      method: 'POST',
      headers: {
        Authorization: 'Bearer ' + state.token,
        'Content-Type': 'application/json',
      },
      url: `${REMOTE_HOST}/announcements/${data.id}/messages/send`,
      data: { message },
    };

    axios(options)
      .then(d => {
        console.log(d.data);
      })
      .catch(() => {});
  };

  return (
    <StyledForm onSubmit={handleSubmit(onSubmit)}>
      <TextField name="message" label="Message" fullWidth multiline />
      <StyledButton variant="contained" type="submit">
        Send
      </StyledButton>
    </StyledForm>
  );
};

export default MessageInput;

const StyledForm = styled.form`
  display: flex;
  align-items: center;
  flex-direction: row;
  margin: 30px;
`;

const StyledButton = styled(Button)`
  width: 80px;
`;
