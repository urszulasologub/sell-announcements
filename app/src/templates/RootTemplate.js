import React from 'react';
import PropTypes from 'prop-types';
import Navbar from 'components/Navbar/Navbar';

const RootTemplate = ({ children }) => (
  <div>
    <Navbar />
    {children}
  </div>
);

RootTemplate.propTypes = {
  children: PropTypes.oneOfType([PropTypes.element, PropTypes.node]).isRequired,
};

export default RootTemplate;
