import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import authService from "../../services/authService";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await authService.login({ username, password });

      if (response && response.token) {
        if (response.roles.includes("ROLE_ADMIN")) {
          navigate("/admin-dashboard");
        } else if (response.roles.includes("ROLE_FLIGHT_OWNER")) {
          navigate("/flight-owner-dashboard");
        } else if (response.roles.includes("ROLE_USER")) {
          navigate("/user-dashboard");
        } else {
          navigate("/");
        }
      } else {
        setError("Invalid credentials. Try again.");
      }
    } catch (err) {
      console.error(err);
      setError(err.response?.data?.message || "Login failed. Try again.");
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100 bg-light">
      <div className="card p-4 shadow-sm border-0" style={{ width: "100%", maxWidth: "400px" }}>
        
        <div className="text-center mb-4">
          <h3 className="fw-bold">Welcome Back</h3>
          <p className="text-muted">Login to your SimplyFly account</p>
        </div>

        <form onSubmit={handleLogin}>
          
          {/* Username */}
          <div className="mb-3">
            <label className="form-label">Username</label>
            <input
              type="text"
              className="form-control"
              placeholder="Enter your username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>

          {/* Password */}
          <div className="mb-3">
            <label className="form-label">Password</label>
            <input
              type="password"
              className="form-control"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          {/* Error message */}
          {error && (
            <div className="alert alert-danger py-2 text-center">
              {error}
            </div>
          )}

          {/* Login button */}
          <button type="submit" className="btn btn-primary btn-lg w-100 mt-2">
            Login
          </button>
        </form>

        {/* Register Link */}
        <div className="text-center mt-3">
          <small>
            Don't have an account?{" "}
            <Link to="/register" className="text-decoration-none fw-semibold">
              Register here
            </Link>
          </small>
        </div>

      </div>
    </div>
  );
};

export default Login;