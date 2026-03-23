import React from "react";
import { useNavigate } from "react-router-dom";
import {
  FaPlane,
  FaListUl,
  FaSearch,
  FaRoute,
  FaUserEdit
} from "react-icons/fa";
import "./AdminDashboard.css"; // ✅ reuse SAME CSS

const FlightOwnerDashboard = () => {
  const navigate = useNavigate();

  const dashboardItems = [
    {
      title: "Add Flight",
      link: "/add-flight",
      color: "flight",
      icon: <FaPlane />
    },
    {
      title: "Display Flights",
      link: "/display-flights",
      color: "booking",
      icon: <FaListUl />
    },
    {
      title: "Search Flights",
      link: "/search-flight",
      color: "route",
      icon: <FaSearch />
    },
    {
      title: "Add Route",
      link: "/add-route",
      color: "flight",
      icon: <FaRoute />
    },
    {
      title: "Display Routes",
      link: "/display-routes",
      color: "booking",
      icon: <FaListUl />
    },
    {
      title: "Search Routes",
      link: "/search-route",
      color: "route",
      icon: <FaSearch />
    }
    // {
    //   title: "Edit Profile",
    //   link: "/edit-user",
    //   color: "user",
    //   icon: <FaUserEdit />
    // }
  ];

  return (
    <div className="container text-center mt-5">
      <h2 className="mb-4 fw-bold admin-title">
        Welcome, Flight Owner
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

export default FlightOwnerDashboard;