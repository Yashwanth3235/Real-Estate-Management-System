import React, { useState } from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import AddPropertyForm from './components/AddPropertyForm';
import AdminDashboard from './components/AdminDashboard';
import Header from './components/Header';
import ManageClients from './components/ManageClients';
import ManageListings from './components/ManageListings';
import PropertyDetails from './components/PropertyDetails'; // Add this import
import SearchBar from './components/SearchBar';
import TransactionPage from './components/TransactionPage';
import AboutPage from './pages/AboutPage';
import AgentDashboard from './pages/AgentDashboard';
import AgentPage from './pages/AgentPage';
import AgentsPage from './pages/AgentsPage';
import ClientDashboard from './pages/ClientDashboard';
import ContactPage from './pages/ContactPage';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import OrderSummaryPage from './pages/OrderSummaryPage';
import PropertiesPage from './pages/PropertiesPage';
import RegistrationPage from './pages/RegistrationPage';
import TransactionsPage from './pages/TransactionsPage';

const App = () => {
  const [users, setUsers] = useState([
    { id: 1, name: 'Villa', email: 'Chennai', role: '123*12', mobile: '9999999999' },
    { id: 2, name: 'Plot', email: 'Telangana', role: '345*563', mobile: '99999999' },
    { id: 3, name: 'House', email: 'Andhra Pradesh', role: '140*245', mobile: '888888888' },
  ]);

  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/about" element={<AboutPage />} />
        <Route path="/contact" element={<ContactPage />} />
        <Route path="/properties" element={<PropertiesPage />} />
        <Route path="/agent" element={<AgentPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegistrationPage />} />
        <Route path="/client-dashboard" element={<ClientDashboard />} />
        <Route path="/agent-dashboard" element={<AgentDashboard users={users} setUsers={setUsers} />} />
        <Route path="/agent-dashboard" element={<AgentDashboard />} />
        <Route path="/admin-dashboard" element={<AdminDashboard users={users} setUsers={setUsers}/>} />
        <Route path="/search" element={<SearchBar />} />
        <Route path="/add-property" element={<AddPropertyForm users={users} setUsers={setUsers} />} />
        <Route path="/transaction" element={<TransactionPage />} />
        <Route path="/order-summary" element={<OrderSummaryPage />} />
        <Route path="/agents" element={<AgentsPage />} />
        <Route path="/property/:id" element={<PropertyDetails />} />
        <Route path="/listings" element={<ManageListings users={users} setUsers={setUsers} />} />
        <Route path="/clients" element={<ManageClients />} />
        <Route path="/transactions" element={<TransactionsPage />} />
        <Route path="/Homepage" element={<HomePage />} />
        
      </Routes>
    </Router>
  );
};

export default App;