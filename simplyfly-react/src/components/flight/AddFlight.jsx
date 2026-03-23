import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const AddFlight = () => {
  const navigate = useNavigate();

  /* -------------------- STATE -------------------- */
  const [flight, setFlight] = useState({
    name: "",
    flightCode: "",
    totalSeats: "",
    cabinBaggageLimit: "",
    checkInBaggageLimit: ""
  });

  const [errors, setErrors] = useState({});
  const [touched, setTouched] = useState({});

  /* -------------------- VALIDATION -------------------- */
  const validateField = (name, value) => {
    let error = "";

    switch (name) {
      case "name":
        if (!value) error = "Flight name is required";
        else if (!/^[A-Za-z ]+$/.test(value))
          error = "Only letters and spaces allowed";
        break;

      case "flightCode":
        if (!value) error = "Flight code is required";
        else if (!/^[A-Z]{2}[0-9]{3}$/.test(value))
          error = "Format: AI123";
        break;

      case "totalSeats":
        if (!value) error = "Total seats required";
        else if (value < 180 || value > 232)
          error = "Seats must be between 180 and 232";
        break;

      case "cabinBaggageLimit":
        if (!value) error = "Cabin baggage required";
        else if (value > 7)
          error = "Max cabin baggage is 7 kg";
        break;

      case "checkInBaggageLimit":
        if (!value) error = "Check-in baggage required";
        else if (value < 15 || value > 20)
          error = "Allowed range: 15–20 kg";
        break;

      default:
        break;
    }

    return error;
  };

  /* -------------------- HANDLERS -------------------- */
  const handleChange = (e) => {
    const { name, value } = e.target;

    setFlight({ ...flight, [name]: value });

    if (touched[name]) {
      setErrors({
        ...errors,
        [name]: validateField(name, value)
      });
    }
  };

  const handleBlur = (e) => {
    const { name, value } = e.target;

    setTouched({ ...touched, [name]: true });
    setErrors({
      ...errors,
      [name]: validateField(name, value)
    });
  };

  /* -------------------- SUBMIT -------------------- */
  const handleSubmit = async (e) => {
    e.preventDefault();

    const newErrors = {};
    Object.keys(flight).forEach((key) => {
      const err = validateField(key, flight[key]);
      if (err) newErrors[key] = err;
    });

    setErrors(newErrors);
    setTouched({
      name: true,
      flightCode: true,
      totalSeats: true,
      cabinBaggageLimit: true,
      checkInBaggageLimit: true
    });

    if (Object.keys(newErrors).length > 0) return;

  
    const token = localStorage.getItem("token");

    const headers = token
      ? { Authorization: `Bearer ${token}` }
      : {};

    try {
      await axios.post(
        "http://localhost:8081/api/flights",
        flight,
        { headers }
      );

      alert("✅ Flight added successfully");
      navigate("/display-flights");

    } catch (err) {
      console.error("Error:", err);

      alert(
        err.response?.data?.message ||
        "❌ Server not reachable. Check backend."
      );
    }
  };

  /* -------------------- UI -------------------- */
  return (
    <div className="container col-md-6 mt-4">

      {/* HEADER */}
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h3 className="text-primary fw-bold">✈️ Add Flight</h3>
        <button
          className="btn btn-outline-secondary"
          onClick={() => navigate(-1)}
        >
          ⬅ Back
        </button>
      </div>

      {/* FORM */}
      <form className="card p-4 shadow-sm" onSubmit={handleSubmit}>

        {/* Flight Name */}
        <div className="mb-3">
          <label className="form-label">Flight Name</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={flight.name}
            onChange={handleChange}
            onBlur={handleBlur}
          />
          {touched.name && errors.name && (
            <small className="text-danger">{errors.name}</small>
          )}
        </div>

        {/* Flight Code */}
        <div className="mb-3">
          <label className="form-label">Flight Code</label>
          <input
            type="text"
            className="form-control"
            name="flightCode"
            value={flight.flightCode}
            onChange={handleChange}
            onBlur={handleBlur}
          />
          {touched.flightCode && errors.flightCode && (
            <small className="text-danger">{errors.flightCode}</small>
          )}
        </div>

        {/* Total Seats */}
        <div className="mb-3">
          <label className="form-label">Total Seats</label>
          <input
            type="number"
            className="form-control"
            name="totalSeats"
            value={flight.totalSeats}
            onChange={handleChange}
            onBlur={handleBlur}
          />
          {touched.totalSeats && errors.totalSeats && (
            <small className="text-danger">{errors.totalSeats}</small>
          )}
        </div>

        {/* Cabin Baggage */}
        <div className="mb-3">
          <label className="form-label">Cabin Baggage Limit (kg)</label>
          <input
            type="number"
            className="form-control"
            name="cabinBaggageLimit"
            value={flight.cabinBaggageLimit}
            onChange={handleChange}
            onBlur={handleBlur}
          />
          {touched.cabinBaggageLimit && errors.cabinBaggageLimit && (
            <small className="text-danger">{errors.cabinBaggageLimit}</small>
          )}
        </div>

        {/* Check-in Baggage */}
        <div className="mb-3">
          <label className="form-label">Check-in Baggage Limit (kg)</label>
          <input
            type="number"
            className="form-control"
            name="checkInBaggageLimit"
            value={flight.checkInBaggageLimit}
            onChange={handleChange}
            onBlur={handleBlur}
          />
          {touched.checkInBaggageLimit && errors.checkInBaggageLimit && (
            <small className="text-danger">
              {errors.checkInBaggageLimit}
            </small>
          )}
        </div>

        {/* SUBMIT */}
        <div className="text-center">
          <button className="btn btn-success px-5">
            Add Flight
          </button>
        </div>

      </form>
    </div>
  );
};

export default AddFlight;
