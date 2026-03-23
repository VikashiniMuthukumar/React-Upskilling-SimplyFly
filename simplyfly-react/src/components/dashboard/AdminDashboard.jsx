import React from "react";
import { useNavigate } from "react-router-dom";
import { FaPlane, FaRoute, FaBook, FaUsers } from "react-icons/fa";
import "./AdminDashboard.css";

const AdminDashboard = () => {
  const navigate = useNavigate();

  const dashboardItems = [
    { title: "Manage Flights", link: "/search-flight", color: "flight", icon: <FaPlane /> },
    { title: "Manage Routes", link: "/search-route", color: "route", icon: <FaRoute /> },
    { title: "View Bookings", link: "/display-all-bookings", color: "booking", icon: <FaBook /> },
    { title: "Manage Users", link: "/display-all-users", color: "user", icon: <FaUsers /> }
  ];

  return (
    <div className="container text-center mt-5">
      <h2 className="mb-4 fw-bold admin-title">Admin Dashboard</h2>

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

export default AdminDashboard;