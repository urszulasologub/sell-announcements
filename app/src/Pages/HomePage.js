import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import RootTemplate from 'templates/RootTemplate';
import AnnouncementItem from 'components/Announcement/AnnouncementItem';
import { REMOTE_HOST } from 'config';
import CircularProgress from '@material-ui/core/CircularProgress';
import Alert from '@material-ui/lab/Alert';

const HomePage = () => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const options = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
      url: `${REMOTE_HOST}/announcements`,
    };

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
      <Wrapper>
        {error ? <Alert severity="error">{error}</Alert> : null}
        {loading ? <StyledCircularProgress /> : null}
        <h4>Announcement:</h4>
        {data ? data.map(el => <AnnouncementItem data={el} key={el.id} />) : null}
      </Wrapper>
    </RootTemplate>
  );
};

export default HomePage;

const StyledCircularProgress = styled(CircularProgress)`
  margin: 0 auto;
`;

const Wrapper = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  margin: 30px auto;
  width: 90%;
`;
