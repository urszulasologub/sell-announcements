import React from 'react';
import RootTemplate from 'templates/RootTemplate';
import CategoryList from 'components/Category/CategoryList';

const AdminPage = () => {
  return (
    <RootTemplate>
      <CategoryList />
    </RootTemplate>
  );
};

export default AdminPage;
