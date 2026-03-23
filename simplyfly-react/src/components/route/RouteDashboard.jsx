import React from "react";
import { useNavigate } from "react-router-dom";
import { FaPlane, FaListUl, FaSearch, FaArrowLeft } from "react-icons/fa";
import "../dashboard/AdminDashboard.css"; 

const FlightDashboard = () => {
  const navigate = useNavigate();

  const dashboardItems = [
    { title: "Add Route", link: "/add-route", color: "flight", icon: <FaPlane /> },
    { title: "View Routes", link: "/display-routes", color: "booking", icon: <FaListUl /> },
    { title: "Search Route", link: "/search-route", color: "route", icon: <FaSearch /> }
  ];

  return (
    <div className="container mt-5 text-center">
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2 className="fw-bold admin-title">Flight Dashboard</h2>
        <button
          className="btn btn-outline-secondary"
          onClick={() => navigate("/admin-dashboard")}
        >
          <FaArrowLeft /> Back
        </button>
      </div>

      <div className="row justify-content-center">
        {dashboardItems.map((item, index) => (
          <div key={index} className="col-10 col-sm-6 col-md-4 col-lg-3 mb-4">
            <div
              className={`dashboard-card ${item.color}`}
              onClick={() => navigate(item.link)}
            >
              <div className="dashboard-icon">{item.icon}</div>
              <h5>{item.title}</h5>
              <p>Click to manage</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default FlightDashboard;