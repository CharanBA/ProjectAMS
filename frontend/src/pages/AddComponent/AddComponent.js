import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { addComponent } from '../../api/componentApi';
import { handleApiError } from '../../utils/helpers';
import './AddComponent.css';

const AddComponent = () => {
  const [name, setName] = useState('');
  const [category, setCategory] = useState('');
  const [manufacturer, setManufacturer] = useState('');
  const [serialNumber, setSerialNumber] = useState('');
  const [warranty, setWarranty] = useState('');
  const [success, setSuccess] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newComponent = { name, category, manufacturer, serialNumber, warrantyEnd: warranty };

    try {
      await addComponent(newComponent);
      setSuccess(true);
      setError('');

      
      setName('');
      setCategory('');
      setManufacturer('');
      setSerialNumber('');
      setWarranty('');

    
      setTimeout(() => {
        navigate('/dashboard');
      }, 2000);
    } catch (error) {
      alert(handleApiError(error));
    }
  };

  return (
    <div className="add-component-container">
      <h2>Add New Component</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Component Name"
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
        <input
          type="text"
          placeholder="Manufacturer"
          value={manufacturer}
          onChange={(e) => setManufacturer(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Serial Number"
          value={serialNumber}
          onChange={(e) => setSerialNumber(e.target.value)}
          required
        />
        <input
          type="date"
          placeholder="Warranty Expiry Date"
          value={warranty}
          onChange={(e) => setWarranty(e.target.value)}
          required
        />
        <button type="submit">Add Component</button>
      </form>
      {success && <p className="success-message">Component added successfully! Redirecting...</p>}
      {error && <p className="error-message">{error}</p>}
    </div>
  );
};

export default AddComponent;
