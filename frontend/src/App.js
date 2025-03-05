import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard/Dashboard';
import AddAsset from './pages/AddAsset/AddAsset';
import AddComponent from './pages/AddComponent/AddComponent';
import AssignComponent from './pages/AssignComponent/AssignComponent';
import AssetDetails from './pages/AssetDetails/AssetDetails';
import Navbar from './components/Navbar';
import './App.css';

function App() {
  return (
    <div className="App">
      <Navbar />
      
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/add-asset" element={<AddAsset />} />
        <Route path="/add-component" element={<AddComponent />} />
        <Route path="/assign-component" element={<AssignComponent />} />
        <Route path="/asset/:id" element={<AssetDetails />} />
      </Routes>
    </div>
  );
}

export default App;
