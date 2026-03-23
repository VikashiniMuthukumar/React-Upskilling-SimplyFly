import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import RouteService from "../../services/RouteService";
import FlightService from "../../services/FlightService";

const AddRoute = () => {
  const navigate = useNavigate(); // for back navigation
  const [flights, setFlights] = useState([]);

  const [route, setRoute] = useState({
    flightCode: "",
    origin: "",
    destination: "",
    baseFare: "",
    scheduleDate: "",
    departureTime: "",
    arrivalTime: ""
  });

  // Load flight codes
  useEffect(() => {
    fetchFlights();
  }, []);

  const fetchFlights = async () => {
    try {
      const response = await FlightService.getAllFlights();
      setFlights(response.data);
    } catch (error) {
      console.error("Error fetching flights:", error);
    }
  };

  const handleChange = (e) => {
    setRoute({
      ...route,
      [e.target.name]: e.target.value
    });
  };


const handleSubmit = async (e) => {
  e.preventDefault();

  // ORIGIN & DESTINATION VALIDATION
  if (
    route.origin.trim().toLowerCase() ===
    route.destination.trim().toLowerCase()
  ) {
    alert("Origin and Destination cannot be the same");
    return;
  }

  // TIME VALIDATION
  let dep = new Date(`1970-01-01T${route.departureTime}`);
  let arr = new Date(`1970-01-01T${route.arrivalTime}`);

  // HANDLE OVERNIGHT FLIGHTS
  if (arr <= dep) {
    arr.setDate(arr.getDate() + 1);
  }

  // CALCULATE DURATION
  const duration = (arr - dep) / (1000 * 60); // minutes

  if (duration === 0) {
    alert("Arrival time cannot be the same as departure time");
    return;
  }

  if (duration < 30) {
    alert("Flight duration must be at least 30 minutes");
    return;
  }

  if (duration > 1440) {
    alert("Flight duration cannot exceed 24 hours");
    return;
  }

  try {

    const payload = {
      flightCode: route.flightCode,
      origin: route.origin,
      destination: route.destination,
      baseFare: Number(route.baseFare),
      scheduleDate: route.scheduleDate,
      departureTime: route.departureTime,
      arrivalTime: route.arrivalTime
    };

    console.log("Route Payload:", payload);

    await RouteService.addRoute(payload);

    alert("Route added successfully!");

    setRoute({
      flightCode: "",
      origin: "",
      destination: "",
      baseFare: "",
      scheduleDate: "",
      departureTime: "",
      arrivalTime: ""
    });

  } catch (error) {

    console.error("Error adding route:", error);
    alert("Failed to add route");

  }
};


  const today = new Date().toISOString().split("T")[0];
  const maxDateObj = new Date();
  maxDateObj.setMonth(maxDateObj.getMonth() + 3);
  const maxDate = maxDateObj.toISOString().split("T")[0];

  return (
    <div className="container mt-4">

      {/* ================= HEADER with Back Button ================= */}
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h3 className="text-primary fw-bold">✈️ Add Route</h3>
        <button
          className="btn btn-outline-secondary"
          onClick={() => navigate(-1)}
        >
          ⬅ Back
        </button>
      </div>

      {/* ================= FORM ================= */}
      <form onSubmit={handleSubmit} className="card p-4 shadow-sm">

        {/* Flight Dropdown */}
        <div className="mb-3">
          <label>Flight</label>
          <select
            name="flightCode"
            value={route.flightCode}
            onChange={handleChange}
            className="form-control"
            required
          >
            <option value="">Select Flight</option>
            {flights.map((flight) => (
              <option key={flight.flightId} value={flight.flightCode}>
                {flight.flightCode} - {flight.name}
              </option>
            ))}
          </select>
        </div>

        <div className="mb-3">
          <label>Origin</label>
          <input
            type="text"
            name="origin"
            value={route.origin}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label>Destination</label>
          <input
            type="text"
            name="destination"
            value={route.destination}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label>Base Fare</label>
          <input
            type="number"
            name="baseFare"
            value={route.baseFare}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label>Schedule Date</label>
          <input
            type="date"
            name="scheduleDate"
            value={route.scheduleDate}
            onChange={handleChange}
            className="form-control"
            min={today}
            max={maxDate}
            required
          />
        </div>

        <div className="mb-3">
          <label>Departure Time</label>
          <input
            type="time"
            name="departureTime"
            value={route.departureTime}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="mb-3">
          <label>Arrival Time</label>
          <input
            type="time"
            name="arrivalTime"
            value={route.arrivalTime}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>

        <div className="text-center">
          <button className="btn btn-primary px-5">Add Route</button>
        </div>
      </form>
    </div>
  );
};

export default AddRoute;