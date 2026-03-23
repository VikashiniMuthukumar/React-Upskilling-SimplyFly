import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import authService from "../../services/authService";

const Register = () => {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    username: "",
    password: "",
    email: "",
    role: "",
  });

  const [message, setMessage] = useState("");

  /* ✅ Validation Patterns */
  const usernamePattern = /^[A-Z][a-zA-Z]*$/;
  const passwordPattern =
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}$/;
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    setMessage("");
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    setMessage("");

    /* 🔴 Frontend Validations */
    if (!form.username) {
      setMessage("Username is required");
      return;
    }

    if (!usernamePattern.test(form.username)) {
      setMessage(
        "Username must start with a capital letter and contain only alphabets"
      );
      return;
    }

    if (!form.password) {
      setMessage("Password is required");
      return;
    }

    if (!passwordPattern.test(form.password)) {
      setMessage(
        "Password must contain capital, small, number and special character"
      );
      return;
    }

    if (!form.email) {
      setMessage("Email is required");
      return;
    }

    if (!emailPattern.test(form.email)) {
      setMessage("Please enter a valid email address");
      return;
    }

    if (!form.role) {
      setMessage("Please select a role");
      return;
    }

    /* 🔵 Backend Call */
    try {
      await authService.register(form);
      alert("Registration successful");
      navigate("/login");
    } catch (error) {
      if (error.response) {
        const status = error.response.status;

        if (status === 409) {
          setMessage("Username already exists. Please try another one.");
        } else if (status === 400) {
          setMessage("Invalid data provided");
        } else if (status === 403) {
          setMessage("Access denied");
        } else if (status >= 500) {
          setMessage("Server error. Please try again later.");
        } else {
          setMessage("Registration failed. Please check your details.");
        }
      } else {
        setMessage("Network error. Please check your internet connection.");
      }
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100 bg-light">
      <div
        className="card p-4 shadow-sm border-0"
        style={{ maxWidth: 450, width: "100%" }}
      >
        <div className="text-center mb-3">
          <h4 className="fw-bold">Create Account</h4>
          <p className="text-muted">Register to use SimplyFly</p>
        </div>

        {message && (
          <div className="alert alert-danger text-center py-2">
            {message}
          </div>
        )}

        <form onSubmit={handleRegister}>
          {/* Username */}
          <div className="mb-3">
            <label className="form-label">Username</label>
            <input
              name="username"
              className="form-control"
              placeholder="Eg: Ram"
              value={form.username}
              onChange={handleChange}
            />
          </div>

          {/* Password */}
          <div className="mb-3">
            <label className="form-label">Password</label>
            <input
              type="password"
              name="password"
              className="form-control"
              placeholder="Eg: Ram@123"
              value={form.password}
              onChange={handleChange}
            />
          </div>

          {/* Email */}
          <div className="mb-3">
            <label className="form-label">Email</label>
            <input
              name="email"
              className="form-control"
              placeholder="Eg: ram@gmail.com"
              value={form.email}
              onChange={handleChange}
            />
          </div>

          {/* Role */}
          <div className="mb-3">
            <label className="form-label">Select Role</label>
            <select
              name="role"
              className="form-control"
              value={form.role}
              onChange={handleChange}
            >
              <option value="">Select Role</option>
              <option value="ROLE_USER">User</option>
              <option value="ROLE_ADMIN">Admin</option>
              <option value="ROLE_FLIGHT_OWNER">Flight Owner</option>
            </select>
          </div>

          <button className="btn btn-success w-100 mt-2">
            Register
          </button>
        </form>

        <div className="text-center mt-3">
          <small>
            Already have an account?{" "}
            <Link to="/login" className="fw-semibold">
              Login here
            </Link>
          </small>
        </div>
      </div>
    </div>
  );
};

export default Register;