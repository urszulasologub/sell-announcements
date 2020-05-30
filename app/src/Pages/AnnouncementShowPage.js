import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import { REMOTE_HOST } from 'config';
import RootTemplate from 'templates/RootTemplate';
import CircularProgress from '@material-ui/core/CircularProgress';
import Alert from '@material-ui/lab/Alert';
import noImage from 'assets/noImage.png';

const AnnouncementShowPage = value => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const options = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
      url: `${REMOTE_HOST}/announcements/${value.match.params.id}`,
    };

    console.log(options);

    setLoading(true);

    axios(options)
      .then(e => {
        setData(e.data);
      })
      .catch(e => {
        setError('Failed to load data');
      })
      .then(() => {
        setLoading(false);
      });
  }, []);

  return (
    <RootTemplate>
      {console.log(data)}
      {error ? <Alert severity="error">{error}</Alert> : null}
      {loading ? <StyledCircularProgress /> : null}
      <Card>
        <Wrapper>
          {/* {data.image ? <Img background={REMOTE_HOST + data.image} /> : <Img />} */}
          <h1>OGŁOSZENIE</h1>
        </Wrapper>
      </Card>
    </RootTemplate>
  );
};

export default AnnouncementShowPage;

const StyledCircularProgress = styled(CircularProgress)`
  margin: 0 auto;
`;

const Card = styled.div`
  background-color: white;
  border-radius: 5px;
  box-shadow: 0 5px 10px -5px rgba(0, 0, 0, 0.4);
  margin: 10px auto;
  width: 80%;
`;

const Wrapper = styled.div`
  margin: 10px 20px;
  display: flex;
  flex-direction: column;
`;

const Img = styled.li`
  list-style: none;
  width: 130px;
  height: 130px;
  background-color: #e3e3e3;
  background-image: url(${({ background }) => (background ? background : noImage)});
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 4px;
`;
