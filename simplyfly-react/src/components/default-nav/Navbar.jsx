import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();

  const username = localStorage.getItem("username");
  const role = localStorage.getItem("role");

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  const getRoleLabel = () => {
    if (role === "ROLE_ADMIN") return "Admin";
    if (role === "ROLE_USER") return "User";
    if (role === "ROLE_FLIGHT_OWNER") return "Flight Owner";
    return "";
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-info">
      <div className="container-fluid">

        {/* Left: Logo */}
        <Link className="navbar-brand d-flex align-items-center" to="/">
          <i className="fas fa-plane-departure me-2"></i>
          SimplyFly
        </Link>

        {/* Middle: Role-based menus */}
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav me-auto">

            {/* ADMIN MENU */}
            {role === "ROLE_ADMIN" && (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/admin-dashboard">
                    Dashboard
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/display-users">
                    Users
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/display-all-booking">
                    All Bookings
                  </Link>
                </li>
              </>
            )}

            {/* USER MENU */}
            {role === "ROLE_USER" && (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/user-dashboard">
                    Dashboard
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/add-booking">
                    Book Flight
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/display-bookings">
                    My Bookings
                  </Link>
                </li>
              </>
            )}

            {/* FLIGHT OWNER MENU */}
            {role === "ROLE_FLIGHT_OWNER" && (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/flight-owner-dashboard">
                    Dashboard
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/add-flight">
                    Add Flight
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/display-flights">
                    My Flights
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/add-route">
                    Add Route
                  </Link>
                </li>
              </>
            )}

          </ul>
        </div>

        {/* Right: Auth section */}
        <div className="ms-auto d-flex align-items-center">

          {/* Not Logged In */}
          {!username && (
            <>
              <Link to="/login" className="btn btn-outline-light me-2">
                Login
              </Link>
              <Link to="/register" className="btn btn-light text-info">
                Register
              </Link>
            </>
          )}

          {/* Logged In */}
          {username && (
            <>
              <span className="text-white me-3 fw-semibold">
                Hi, {username}
                {/* , {getRoleLabel()} */}
              </span>
              <button
                className="btn btn-outline-light"
                onClick={logout}
              >
                Logout
              </button>
            </>
          )}

        </div>

      </div>
    </nav>
  );
};

export default Navbar;
