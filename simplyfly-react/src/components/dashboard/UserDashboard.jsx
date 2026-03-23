import React from "react";
import { useNavigate } from "react-router-dom";
import { FaBook, FaClipboardList, FaSearch } from "react-icons/fa";
import "./AdminDashboard.css"; 

const UserDashboard = () => {
  const navigate = useNavigate();
  const username = localStorage.getItem("username") || "User";

  const dashboardItems = [
    {
      title: "Add Booking",
      link: "/add-booking",
      color: "flight",
      icon: <FaBook />
    },
    {
      title: "My Bookings",
      link: "/display-bookings",
      color: "booking",
      icon: <FaClipboardList />
    },
    {
      title: "Search Booking",
      link: "/search-booking",
      color: "user",
      icon: <FaSearch />
    }
  ];

  return (
    <div className="container text-center mt-5">
      <h2 className="mb-4 fw-bold admin-title">
        Welcome, {username}
      </h2>

      <div className="row justify-content-center">
        {dashboardItems.map((item, index) => (
          <div
            key={index}
            className="col-10 col-sm-6 col-md-4 col-lg-3 mb-4"
          >
            <div
              className={`dashboard-card ${item.color}`}
              onClick={() => navigate(item.link)}
            >
              <div className="dashboard-icon">{item.icon}</div>
              <h5>{item.title}</h5>
              <p>Click to open</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default UserDashboard;