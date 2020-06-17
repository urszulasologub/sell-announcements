import React from 'react';
import RootTemplate from 'templates/RootTemplate';
import CategoryList from 'components/Category/CategoryList';
import Chart from 'components/Chart/Chart';

const AdminPage = () => {
  return (
    <RootTemplate>
      <Chart />
      <CategoryList />
    </RootTemplate>
  );
};

export default AdminPage;
