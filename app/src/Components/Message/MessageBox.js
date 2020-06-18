import React, { useState } from 'react';
import styled from 'styled-components';
import MessageInput from 'components/Message/MessageInput';

const MessageBox = ({ data }) => {
  const [messageGroup, setMessageGroup] = useState(null);
  console.log(data);

  return (
    <Card>
      <MessageInput data={data} />
    </Card>
  );
};

export default MessageBox;

const Card = styled.div`
  background-color: white;
  border-radius: 5px;
  box-shadow: 0 5px 10px -5px rgba(0, 0, 0, 0.4);
  margin: 10px auto;
  width: 60%;
  display: flex;
  flex-direction: column;

  @media (max-width: 1100px) {
    width: 80%;
  }
`;
