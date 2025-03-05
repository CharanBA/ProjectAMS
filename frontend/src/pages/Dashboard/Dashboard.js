import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import './Dashboard.css';
import { handleApiError } from '../../utils/helpers';

function Dashboard() {
  const [assets, setAssets] = useState([]);
  const [expanded, setExpanded] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:8080/api/asset/all')
      .then(response => {
        setAssets(response.data.reverse());
      })
      .catch(error => {
        alert(handleApiError(error));
      });
  }, []);

  const fetchComponents = (assetId) => {
    axios.get(`http://localhost:8080/api/component/asset/${assetId}/components`)
      .then(response => {
        setAssets(prevAssets =>
          prevAssets.map(asset =>
            asset.id === assetId ? { ...asset, components: response.data } : asset
          )
        );
      })
      .catch(error => {
        alert(handleApiError(error));
      });
  };

  const toggleExpand = (assetId) => {
    if (expanded === assetId) {
      setExpanded(null); // Collapse if already expanded
    } else {
      setExpanded(assetId);
      const asset = assets.find(a => a.id === assetId);
      if (!asset.components) {
        fetchComponents(assetId);
      }
    }
  };

  return (
    <div className="dashboard">
      <h2>Asset List</h2>
      <div className="add-asset-wrapper">
        <Link to="/add-asset" className="add-asset-button-top"> Add Asset</Link>
      </div>
      <table className="asset-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Category</th>
            <th>Description</th>
            <th>Purchase Date</th>
            <th>Status</th>
            <th>Assigned To</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {assets.map(asset => (
            <>
              <tr key={asset.id}>
                <td>{asset.name}</td>
                <td>{asset.category}</td>
                <td>{asset.description}</td>
                <td>{asset.purchaseDate}</td>
                <td>{asset.status}</td>
                <td>{asset.assignedTo}</td>
                <td>
                  <button 
                    className="toggle-button" 
                    onClick={() => toggleExpand(asset.id)}
                  >
                    {expanded === asset.id ? 'Collapse' : 'Expand'}
                  </button>
                  <Link className="details-link" to={`/asset/${asset.id}`}>Details</Link>
                </td>
              </tr>
              {expanded === asset.id && (
                <tr className="expanded-row">
                  <td colSpan="7">
                    <strong>Components:</strong>
                    <ul>
                      {asset.components && asset.components.length > 0 ? (
                        asset.components.map((comp, index) => (
                          <li key={index}>
                            {comp.name} ({comp.category}), {comp.manufacturer} - 
                            Serial: {comp.serialNumber}, Warranty: {comp.warrantyEnd}
                          </li>
                        ))
                      ) : (
                        <li>No components assigned</li>
                      )}
                    </ul>
                  </td>
                </tr>
              )}
            </>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Dashboard;
