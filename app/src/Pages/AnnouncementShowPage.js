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
  }, [value.match.params.id]);

  return (
    <RootTemplate>
      {error ? <Alert severity="error">{error}</Alert> : null}
      {loading ? <StyledCircularProgress /> : null}
      {data ? (
        <Card>
          <Wrapper>
            {data.image ? <Img background={REMOTE_HOST + data.image} /> : <Img />}
            <TitleWrapper>
              <StyledName>{data.name}</StyledName>
              <StyledPrice>{data.price} $</StyledPrice>
              <StyledLocation>location: {data.location}</StyledLocation>
              <StyledLocation>phone number: {data.phone_number}</StyledLocation>
            </TitleWrapper>
          </Wrapper>
          <StyledDescriptionTitle>Description:</StyledDescriptionTitle>
          <StyledDescription>{data.description}</StyledDescription>
          <br />
        </Card>
      ) : null}
    </RootTemplate>
  );
};

export default AnnouncementShowPage;

const StyledCircularProgress = styled(CircularProgress)`
  margin: 0 auto;
`;

const TitleWrapper = styled.div`
  display: flex;
  flex-direction: column;
  margin: 10px 15px;
`;

const StyledName = styled.div`
  font-size: 25px;
  font-weight: bold;
`;

const StyledDescriptionTitle = styled.div`
  margin: 10px 20px;
  font-size: 20px;
  font-weight: bold;
`;

const StyledDescription = styled.div`
  margin: 5px 20px;
  font-size: 15px;
`;

const Card = styled.div`
  background-color: white;
  border-radius: 5px;
  box-shadow: 0 5px 10px -5px rgba(0, 0, 0, 0.4);
  margin: 10px auto;
  width: 60%;
`;

const Wrapper = styled.div`
  margin: 10px 20px;
  display: flex;
  flex-direction: row;
`;

const Img = styled.div`
  list-style: none;
  width: 350px;
  height: 350px;
  background-color: #e3e3e3;
  background-image: url(${({ background }) => (background ? background : noImage)});
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 4px;
  margin: 15px 0;
`;

const StyledLocation = styled.div`
  font-size: 15px;
  font-weight: bold;
  margin: 5px 0;
`;

const StyledPrice = styled.div`
  font-size: 20px;
  font-weight: bold;
  margin: 10px 0 0;
`;
