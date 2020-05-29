import React from 'react';
import RootTemplate from 'templates/RootTemplate';
import AnnouncementForm from 'components/Announcement/AnnouncementForm';

const HomePage = () => {
  return (
    <RootTemplate>
      <AnnouncementForm />
    </RootTemplate>
  );
};

export default HomePage;
