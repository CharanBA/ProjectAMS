import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import './AssetDetails.css';
import axios from 'axios';
import { handleApiError } from '../../utils/helpers';

const AssetDetails = () => {
  const { id } = useParams();
  const [asset, setAsset] = useState(null);
  const [components, setComponents] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch Asset Details
    axios.get(`http://localhost:8080/api/asset?id=${id}`)
      .then(response => {
        setAsset(response.data);
        setError(null);
      })
      .catch(error => {
        const message = handleApiError(error);
        setError(message);
      });

    // Fetch Components Linked to Asset
    axios.get(`http://localhost:8080/api/component/asset/${id}/components`)
      .then(response => {
        setComponents(response.data);
      })
      .catch(error => {
        console.error("Error fetching components:", error);
      });

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
