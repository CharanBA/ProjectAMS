import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { addAsset } from '../../api/assetApi';
import { handleApiError } from '../../utils/helpers';
import './AddAsset.css';

const AddAsset = () => {
  const [name, setName] = useState('');
  const [category, setCategory] = useState('');
  const [description, setDescription] = useState('');
  const [purchaseDate, setPurchaseDate] = useState('');
  const [status, setStatus] = useState('');
  const [assignedTo, setAssignedTo] = useState('');
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newAsset = { name, category, description, purchaseDate, status, assignedTo };

    try {
      await addAsset(newAsset);
      setSuccess(true);

      // Reset form fields
      setName('');
      setCategory('');
      setDescription('');
      setPurchaseDate('');
      setStatus('');
      setAssignedTo('');

      // Redirect to dashboard after 2 seconds
      setTimeout(() => {
        navigate('/dashboard');
      }, 2000);
    } catch (error) {
      alert(handleApiError(error));
    }
  };

  return (
    <div className="add-asset-container">
      <h2>Add New Asset</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Asset Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Category"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
          required
        />
        <textarea
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />
        <input
          type="date"
          value={purchaseDate}
          onChange={(e) => setPurchaseDate(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Status"
          value={status}
          onChange={(e) => setStatus(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Assigned To"
          value={assignedTo}
          onChange={(e) => setAssignedTo(e.target.value)}
        />
        <button type="submit">Add Asset</button>
      </form>
      {success && <p className="success-message">Asset added successfully! Redirecting...</p>}
    </div>
  );
};

export default AddAsset;
