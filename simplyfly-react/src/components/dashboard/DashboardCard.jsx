import React from "react";
import "./DashboardCard.css";

const DashboardCard = ({ title, icon, gradient, route, navigate }) => {
  return (
    <div
      className="dashboard-card"
      style={{ background: gradient }}
      onClick={() => navigate(route)}
    >
      <div className="card-content">
        <div className="card-icon">{icon}</div>
        <h5>{title}</h5>
      </div>
    </div>
  );
};

export default DashboardCard;
