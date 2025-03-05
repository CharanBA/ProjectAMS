import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import './AssetDetails.css';
import { getAssetById } from '../../api/assetApi';
import { getComponentsByAssetId } from '../../api/componentApi';

const AssetDetails = () => {
  const { id } = useParams();
  const [asset, setAsset] = useState(null);
  const [components, setComponents] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchAssetDetails = async () => {
      try {
        const assetData = await getAssetById(id);
        setAsset(assetData);
        setError(null);
      } catch (error) {
        console.error("Error fetching Assets:", error);
      }
    };

    const fetchComponents = async () => {
      try {
        const componentData = await getComponentsByAssetId(id);
        setComponents(componentData);
      } catch (error) {
        console.error("Error fetching components:", error);
      }
    };

    fetchAssetDetails();
    fetchComponents();
  }, [id]);

  if (error) {
    return <p className="error-message">{error}</p>;
  }

  if (!asset) {
    return <p className="loading">Loading...</p>;
  }

  return (
    <div className="asset-details">
      <h2>{asset.name}</h2>
      <p><strong>Category:</strong> {asset.category}</p>
      <p><strong>Description:</strong> {asset.description}</p>
      <p><strong>Purchase Date:</strong> {asset.purchaseDate}</p>
      <p><strong>Status:</strong> {asset.status}</p>
      <p><strong>Assigned To:</strong> {asset.assignedTo}</p>

      <h3>Components:</h3>
      {components.length > 0 ? (
        <ul className="components-list">
          {components.map((comp, index) => (
            <li key={index}>
              <strong className="component-name">{comp.name}</strong>
              <ul>
                <li>Category: <span className="component-category">{comp.type}</span></li>
                <li>Manufacturer: <span className="component-manufacturer">{comp.manufacturer}</span></li>
                <li>Serial Number: <span className="component-serial">{comp.serialNumber}</span></li>
                <li>Warranty: <span className="component-warranty">{comp.warrantyEnd}</span></li>
              </ul>
            </li>
          ))}
        </ul>
      ) : (
        <p>No components assigned.</p>
      )}

      <Link className="back-link" to="/dashboard">Back to Dashboard</Link>
    </div>
  );
};

export default AssetDetails;
