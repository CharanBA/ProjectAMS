import React, { useState, useEffect } from 'react';
import './AssignComponent.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { handleApiError } from '../../utils/helpers';

const AssignComponent = () => {
  const [assets, setAssets] = useState([]);
  const [components, setComponents] = useState([]);
  const [selectedAsset, setSelectedAsset] = useState('');
  const [selectedComponent, setSelectedComponent] = useState('');
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();

  // Fetch assets and components on component mount
  useEffect(() => {
    axios.get('http://localhost:8080/api/asset/all')
      .then(response => setAssets(response.data))
      .catch(error => console.error('Error fetching assets:', error));

    axios.get('http://localhost:8080/api/component/all')
      .then(response => setComponents(response.data))
      .catch(error => console.error('Error fetching components:', error));
  }, []);

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!selectedAsset || !selectedComponent) {
      alert('Please select both an asset and a component.');
      return;
    }

    console.log('Selected Asset:', selectedAsset);
    console.log('Selected Component:', selectedComponent);

    try {
      const response = await axios.post(`http://localhost:8080/api/asset/${selectedAsset}/component/${selectedComponent}`);
      console.log('Response:', response.data);

      setSuccess(true);
      setSelectedAsset('');
      setSelectedComponent('');

      // Refresh asset list after assignment
      const updatedAssets = await axios.get('http://localhost:8080/api/asset/all');
      setAssets(updatedAssets.data);

      // Redirect to Dashboard after 2 seconds
      setTimeout(() => {
        navigate('/dashboard');
      }, 2000);
    } catch (error) {
      console.error('Error assigning component:', error);
      const message = handleApiError(error);
      alert(message);
    }
  };

  return (
    <div className="assign-component-container">
      <h2>Assign Component to Asset</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="selectAsset">Select Asset:</label>
        <select id="selectAsset" value={selectedAsset} onChange={(e) => setSelectedAsset(e.target.value)} required>
          <option value="">-- Select Asset --</option>
          {assets.map(asset => (
            <option key={asset.id} value={asset.id}>
              {asset.name} ({asset.category})
            </option>
          ))}
        </select>

        <label htmlFor="selectComponent">Select Component:</label>
        <select id="selectComponent" value={selectedComponent} onChange={(e) => setSelectedComponent(e.target.value)} required>
          <option value="">-- Select Component --</option>
          {components.map(component => (
            <option key={component.id} value={component.id}>
              {component.name} ({component.category}) - {component.manufacturer} | Serial: {component.serialNumber} | Warranty: {component.warranty}
            </option>
          ))}
        </select>

        <button type="submit">Assign Component</button>
      </form>

      {success && <p className="success-message">Component assigned successfully! Redirecting...</p>}
    </div>
  );
};

export default AssignComponent;
