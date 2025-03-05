import React, { useState, useEffect } from 'react';
import './AssignComponent.css';
import { useNavigate } from 'react-router-dom';
import { getAllAssets, assignComponentToAsset } from '../../api/assetApi';
import { getAllComponents } from '../../api/componentApi';
import { handleApiError } from '../../utils/helpers';

const AssignComponent = () => {
  const [assets, setAssets] = useState([]);
  const [components, setComponents] = useState([]);
  const [selectedAsset, setSelectedAsset] = useState('');
  const [selectedComponent, setSelectedComponent] = useState('');
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchAssetsAndComponents = async () => {
      try {
        const assetsData = await getAllAssets();
        const componentsData = await getAllComponents();
        setAssets(assetsData);
        setComponents(componentsData);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchAssetsAndComponents();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!selectedAsset || !selectedComponent) {
      alert('Please select both an asset and a component.');
      return;
    }

    try {
      await assignComponentToAsset(selectedAsset, selectedComponent);
      setSuccess(true);
      setSelectedAsset('');
      setSelectedComponent('');

      setTimeout(() => {
        navigate('/dashboard');
      }, 2000);
    } catch (error) {
      console.error('Error assigning component:', error);
      alert(handleApiError(error));
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
              {component.name} ({component.category}) - {component.manufacturer} | Serial: {component.serialNumber} | Warranty: {component.warrantyEnd}
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
