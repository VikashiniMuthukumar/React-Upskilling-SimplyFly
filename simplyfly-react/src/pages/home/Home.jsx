
import React from "react";
import homeImage from "../../assets/home-flight.jpg"; // adjust path if needed

const Home = () => {
  return (
    <div className="container py-5">
      {/* Heading Section */}
      <div className="text-center mb-5">
        <h1 className="fw-bold text-info">Welcome to SimplyFly ✈️</h1>
        <p className="lead text-muted mt-3">
          SimplyFly is your one-stop platform for booking flights, managing routes, and organizing airline operations.
          Whether you're a passenger, admin, or flight owner, we've got you covered with a seamless flying experience.
        </p>
      </div>

      {/* Image Section */}
      <div className="text-center">
        <img
          src={homeImage}
          alt="Flight illustration"
          className="img-fluid"
          style={{ maxHeight: "300px" }}
        />
      </div>
    </div>
  );
};

export default Home;