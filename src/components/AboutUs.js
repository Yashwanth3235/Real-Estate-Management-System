import React from 'react';
import '../styles/AboutUs.css';

const AboutUs = () => {
  return (
    <div className="about-us">
      <section className="header-section">
        <div className="overlay">
          <h1>About us</h1>
          <p>At NxtHome, our mission is to simplify the real estate management process for property owners, managers, and tenants. We believe that managing real estate should be efficient, transparent, and accessible to everyone. Our platform is designed to bridge the gap between landlords and tenants, providing a seamless experience for all users.
</p>
        </div>
      </section>

     
     
      <section className="meet-team">
        <h2>Meet Our Team</h2>
        <div className="team-members">
          <div className="team-member">
            <img src="../images/virat.jpg" alt="Team Member" />
            <h3>Virat Kohli</h3>
            <p>CEO</p>
          </div>
          <div className="team-member">
            <img src="../images/sachin.jpg" alt="Team Member" />
            <h3>sachin Tendular</h3>
            <p>Manager</p>
          </div>
          <div className="team-member">
            <img src="/images/dhoni.jpg" alt="Team Member" />
            <h3>MSD</h3>
            <p>Marketing Manager</p>
          </div>
          <div className="team-member">
            <img src="/images/shikar.jpeg" alt="Team Member" />
            <h3>shikar Dhawan</h3>
            <p>HR</p>
          </div>
          <div className="team-member">
            <img src="/images/hardi.jpeg" alt="Team Member" height={192}/>
            <h3>Hardik</h3>
            <p>Chief Operating Officer</p>
          </div>
          
        </div>
      </section>

      <section className="footer-section">
  
  <div className="footer">
    <div className="footer-col">
      <h4>NxtHome</h4>
      <p>Contact Us: NxtHome@gmail.com</p>
      <p>Phone: (91)984567121 </p>
    </div>
   
   
  </div>
</section>

    </div>
  );
};

export default AboutUs;
