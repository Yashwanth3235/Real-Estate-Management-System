import axios from 'axios';
import React, { useState } from 'react';

const AddProperty = () => {
  const [formData, setFormData] = useState({
    email: '',
    imageUrl: null,
    bhkType: '',
    depositPrice: '',
    location: '',
    description: '',
    ownerName: '',
    ownerContact: '',
    propertyStatus: ''
  });

  const [message, setMessage] = useState('');

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleFileChange = (e) => {
    setFormData({
      ...formData,
      imageUrl: e.target.files[0], // handling file input
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Prepare form data to be sent to backend
    const propertyData = new FormData();
    propertyData.append('email', formData.email);
    propertyData.append('imageUrl', formData.imageUrl);
    propertyData.append('bhkType', formData.bhkType);
    propertyData.append('depositPrice', formData.depositPrice);
    propertyData.append('location', formData.location);
    propertyData.append('description', formData.description);
    propertyData.append('ownerName', formData.ownerName);
    propertyData.append('ownerContact', formData.ownerContact);
    propertyData.append('propertyStatus', formData.propertyStatus);

    try {
      // Sending post request to backend API to save property
      const response = await axios.post('http://localhost:8080/api/properties/create', propertyData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      setMessage(`Property added successfully! ID: ${response.data}`);
    } catch (error) {
      console.error('There was an error adding the property!', error);
      setMessage('Error adding property, please try again.');
    }
  };

  return (
    <div className="container mt-5">
      <h2>Add New Property</h2>
      <form onSubmit={handleSubmit} encType="multipart/form-data">
        <div className="form-group">
          <label>Email (Agent's Email)</label>
          <input
            type="email"
            className="form-control"
            name="email"
            value={formData.email}
            onChange={handleInputChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Property Image</label>
          <input
            type="file"
            className="form-control"
            name="imageUrl"
            accept="image/*"
            onChange={handleFileChange}
            required
          />
        </div>

        <div className="form-group">
          <label>BHK Type (e.g., 2BHK, 3BHK)</label>
          <input
            type="text"
            className="form-control"
            name="bhkType"
            value={formData.bhkType}
            onChange={handleInputChange}
            required
            pattern="^[0-9]+[BHK]{3}$"
          />
        </div>

        <div className="form-group">
          <label>Deposit Price</label>
          <input
            type="number"
            className="form-control"
            name="depositPrice"
            value={formData.depositPrice}
            onChange={handleInputChange}
            required
            min="0"
          />
        </div>

        <div className="form-group">
          <label>Location</label>
          <input
            type="text"
            className="form-control"
            name="location"
            value={formData.location}
            onChange={handleInputChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Description</label>
          <textarea
            className="form-control"
            name="description"
            value={formData.description}
            onChange={handleInputChange}
          />
        </div>

        <div className="form-group">
          <label>Owner Name</label>
          <input
            type="text"
            className="form-control"
            name="ownerName"
            value={formData.ownerName}
            onChange={handleInputChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Owner Contact</label>
          <input
            type="tel"
            className="form-control"
            name="ownerContact"
            value={formData.ownerContact}
            onChange={handleInputChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Property Status</label>
          <select
            className="form-control"
            name="propertyStatus"
            value={formData.propertyStatus}
            onChange={handleInputChange}
            required
          >
            <option value="">Select Status</option>
            <option value="Available">Available</option>
            <option value="Sold">Sold</option>
            <option value="Rented">Rented</option>
          </select>
        </div>

        <button type="submit" className="btn btn-primary mt-3">Add Property</button>
      </form>

      {message && <p className="mt-3">{message}</p>}
    </div>
  );
};

export default AddProperty;
