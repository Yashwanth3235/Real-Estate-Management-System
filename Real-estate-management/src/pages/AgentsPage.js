import React from 'react';
import AgentCard from '../components/AgentCard';
import '../styles/AgentsPage.css';

const AgentsPage = () => {
  
  const agents = [
    {
      id: 1,
      name: "Virat",
      email: "Virat@realestate.com",
      phone: "+91 65238451",
      specialization: "Plots",
      image: "/images/virat.jpg"
    },
    {
      id: 2,
      name: "sachin",
      email: "sachin@realestate.com",
      phone: "+1 (345) 678-9012",
      specialization: "Villas",
      image: "/images/sachin.jpg"
    },
    {
      id: 3,
      name: "rohit",
      email: "rohit@realestate.com",
      phone: "+918446596974",
      specialization: "apartments",
      image: "/images/rohith.jpg"
    },
    {
      id: 4,
      name: "hardik",
      email: "hardik@nxthome.com",
      phone: "+91 884945655489",
      specialization: "Properties",
      image: "/images/hardi.jpeg"
    },
    {
      id: 5,
      name: "shikar",
      email: "shikar@nxthome.com",
      phone: "+91 884945648",
      specialization: "Rented Homes",
      image: "/images/shikar.jpeg"
    },

  ];

  return (
    <div className="agents-page">
      <h1>Our Real Estate Agents</h1>
      <div className="agents-grid">
        {agents.map(agent => (
          <AgentCard key={agent.id} {...agent} />
        ))}
      </div>
    </div>
  );
};

export default AgentsPage;
