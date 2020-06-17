import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import RootTemplate from 'templates/RootTemplate';
import AnnouncementItem from 'components/Announcement/AnnouncementItem';
import { REMOTE_HOST } from 'config';
import CircularProgress from '@material-ui/core/CircularProgress';
import Alert from '@material-ui/lab/Alert';
import TextField from '@material-ui/core/TextField';

const HomePage = () => {
  const [data, setData] = useState(null);
  const [filterData, setFilterData] = useState(null);
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
        setFilterData(e.data);
      })
      .catch(e => {
        setError('Failed to load data');
      })
      .then(() => {
        setLoading(false);
      });
  }, []);

  const filter = value => {
    const regex = new RegExp(`${value}`, 'g');
    setFilterData(data.filter(({ name }) => name.toLowerCase().match(regex)));
  };

  return (
    <RootTemplate>
      <Wrapper>
        {error ? <Alert severity="error">{error}</Alert> : null}
        {loading ? <StyledCircularProgress /> : null}
        <h4>Announcement:</h4>
        <TextFieldWrapper>
          <TextField
            name="search"
            label="Search"
            type="text"
            fullWidth
            onChange={e => {
              filter(e.target.value);
            }}
          />
        </TextFieldWrapper>
        {filterData ? filterData.map(el => <AnnouncementItem data={el} key={el.id} />) : null}
      </Wrapper>
    </RootTemplate>
  );
};

export default HomePage;

const StyledCircularProgress = styled(CircularProgress)`
  margin: 0 auto;
`;

const TextFieldWrapper = styled.div`
  margin: 10px auto;
  width: 50%;
`;

const Wrapper = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  margin: 30px auto;
  width: 90%;
`;
