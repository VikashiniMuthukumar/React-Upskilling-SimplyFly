import React, { useEffect, useState } from "react";
import FlightService from "../../services/FlightService";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const SearchRoute = () => {
  const navigate = useNavigate();

  const [routes, setRoutes] = useState([]);
  const [filteredRoutes, setFilteredRoutes] = useState([]);
  const [flights, setFlights] = useState([]);

  const [filters, setFilters] = useState({
    flightCode: "",
    scheduleDate: ""
  });

  const ROUTE_URL = "http://localhost:8081/api/routes/all";

  // 🔹 Date limits
  const today = new Date().toISOString().split("T")[0];
  const maxDate = (() => {
    const d = new Date();
    d.setMonth(d.getMonth() + 3);
    return d.toISOString().split("T")[0];
  })();

  // 🔹 Load flights and routes
  useEffect(() => {
    // Load flights for dropdown
    FlightService.getAllFlights()
      .then(res => setFlights(res.data))
      .catch(err => console.error(err));

    // Load routes
    axios.get(ROUTE_URL, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    })
      .then(res => {
        setRoutes(res.data);
        setFilteredRoutes(res.data);
      })
      .catch(err => console.error(err));
  }, []);

  // 🔹 Handle input change
  const handleChange = (e) => {
    setFilters({
      ...filters,
      [e.target.name]: e.target.value
    });
  };

  // 🔹 Search logic
  const handleSearch = () => {
    let result = routes;

    if (filters.flightCode !== "") {
      result = result.filter(r => r.flightCode === filters.flightCode);
    }

    if (filters.scheduleDate !== "") {
      result = result.filter(r => r.scheduleDate === filters.scheduleDate);
    }

    setFilteredRoutes(result);

    // Optional: clear filters
    setFilters({
      flightCode: "",
      scheduleDate: ""
    });
  };

  return (
    <div className="container mt-4">

      {/* ================= HEADER with Back Button ================= */}
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h3 className="text-primary fw-bold">🔍 Search Routes</h3>
        <button
          className="btn btn-outline-secondary"
          onClick={() => navigate(-1)}
        >
          ⬅ Back
        </button>
      </div>

      {/* ================= FILTERS ================= */}
      <div className="row g-3 mb-3">

        {/* Flight Dropdown */}
        <div className="col-md-4">
          <select
            className="form-select"
            name="flightCode"
            value={filters.flightCode}
            onChange={handleChange}
          >
            <option value="">All Flights</option>
            {flights.map(f => (
              <option key={f.flightCode} value={f.flightCode}>
                {f.flightCode} - {f.name}
              </option>
            ))}
          </select>
        </div>

        {/* Date */}
        <div className="col-md-4">
          <input
            type="date"
            className="form-control"
            name="scheduleDate"
            value={filters.scheduleDate}
            min={today}
            max={maxDate}
            onChange={handleChange}
          />
        </div>

      </div>

      <button className="btn btn-primary mb-3" onClick={handleSearch}>
        Search
      </button>

      {/* ================= RESULTS TABLE ================= */}
      <table className="table table-bordered">
        <thead className="table-dark">
          <tr>
            <th>Flight</th>
            <th>Origin</th>
            <th>Destination</th>
            <th>Date</th>
            <th>Departure</th>
            <th>Arrival</th>
            <th>Fare</th>
          </tr>
        </thead>
        <tbody>
          {filteredRoutes.length === 0 ? (
            <tr>
              <td colSpan="7" className="text-center">
                No routes found
              </td>
            </tr>
          ) : (
            filteredRoutes.map(r => (
              <tr key={r.route_id}>
                <td>{r.flightCode}</td>
                <td>{r.origin}</td>
                <td>{r.destination}</td>
                <td>{r.scheduleDate}</td>
                <td>{r.departureTime}</td>
                <td>{r.arrivalTime}</td>
                <td>₹{r.baseFare}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

export default SearchRoute;