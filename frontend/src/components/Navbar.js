import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';
import Dashboard from '../pages/Dashboard/Dashboard';

const Navbar = () => {
  return (
    <nav className="navbar">
      
      <Link to="/dashboard">Dashboard</Link>
      <Link to="/add-component">Add Component</Link>
      <Link to="/assign-component">Assign Component</Link>
    </nav>
  );
};

export default Navbar;
