import React, { useState } from "react";
import axios from "axios";
import { addBooking } from "../../services/bookingService";
import BackButton from "../common/BackButton";

const AddBooking = () => {
  const [date, setDate] = useState("");
  const [flights, setFlights] = useState([]);
  const [selectedFlight, setSelectedFlight] = useState(null);
  const [seat, setSeat] = useState("");
  const [totalFare, setTotalFare] = useState(0);
  const [searched, setSearched] = useState(false);

  const username = localStorage.getItem("username");
  const token = localStorage.getItem("token");

  const authHeaders = {
    headers: { Authorization: `Bearer ${token}` }
  };

  const today = new Date().toISOString().split("T")[0];
  const maxDate = new Date();
  maxDate.setMonth(maxDate.getMonth() + 3);
  const maxDateValue = maxDate.toISOString().split("T")[0];

  // Search flights
  const searchFlights = async () => {
    if (!date) {
      alert("Please select a date");
      return;
    }

    try {
      const res = await axios.get(
        `http://localhost:8081/api/flights/date/${date}`,
        authHeaders
      );
      setFlights(res.data);
      setSearched(true);
    } catch (err) {
      console.error("Flight search error:", err);

      if (err.response?.status === 403) {
        alert("Session expired. Please login again.");
        localStorage.clear();
        window.location.href = "/login";
      } else {
        alert("Failed to fetch flights");
      }

      setSearched(true);
    }
  };

  // Select flight
  const selectFlight = (flight) => {
    setSelectedFlight(flight);
    setSeat(1);
    setTotalFare(flight.baseFare ? flight.baseFare : 0);
  };

  // Seat change
  const handleSeatChange = (e) => {
    const value = e.target.value;

    if (value === "") {
      setSeat("");
      setTotalFare(0);
      return;
    }

    const seatValue = Number(value);

    if (seatValue > 6) {
      alert("Maximum 6 seats allowed per booking");
      return;
    }

    if (seatValue > selectedFlight.totalSeats) {
      alert("Seats exceed available seats");
      return;
    }

    setSeat(seatValue);
    setTotalFare(seatValue * selectedFlight.baseFare);
  };

  // Confirm booking
  const confirmBooking = async () => {
    if (!selectedFlight) {
      alert("Please select a flight");
      return;
    }

    if (!seat || seat <= 0) {
      alert("Enter valid seat count");
      return;
    }

    if (seat > selectedFlight.totalSeats) {
      alert("Not enough seats available");
      return;
    }

    const bookingData = {
      username: username,
      flightCode: selectedFlight.flightCode,
      flightName: selectedFlight.name,
      origin: selectedFlight.origin,
      destination: selectedFlight.destination,
      bookDate: date,
      seat: Number(seat),
      baseFare: Number(selectedFlight.baseFare),
      totalFare: Number(selectedFlight.baseFare) * Number(seat),
      bookedAt: new Date().toISOString(),
      departureTime: selectedFlight.departureTime,
      arrivalTime: selectedFlight.arrivalTime,
      status: "CONFIRMED"
    };

    try {
      await addBooking(bookingData);
      alert("Booking successful!");
      window.location.reload();
    } catch (err) {
      console.error("Booking failed:", err);

      if (err.response?.status === 403) {
        alert("Session expired. Please login again.");
        localStorage.clear();
        window.location.href = "/login";
      } else {
        alert(err.response?.data?.message || "Booking failed");
      }
    }
  };

  return (
    <div className="container mt-4">
      <BackButton />

      <h3>Add Booking</h3>

      <div className="mb-3">
        <label>Date</label>
        <input
          type="date"
          className="form-control"
          value={date}
          min={today}
          max={maxDateValue}
          onChange={(e) => setDate(e.target.value)}
        />
      </div>

      <button
        className="btn btn-primary mb-3"
        onClick={searchFlights}
        disabled={!date}
      >
        Search Flights
      </button>

      {searched &&
        (flights.length === 0 ? (
          <div className="alert alert-warning">
            No flights available for this date
          </div>
        ) : (
          <table className="table table-bordered">
            <thead>
              <tr>
                <th>Flight Name</th>
                <th>Flight Code</th>
                <th>Origin</th>
                <th>Destination</th>
                <th>Departure</th>
                <th>Arrival</th>
                <th>Base Fare</th>
                <th>Seats</th>
                <th>Select</th>
              </tr>
            </thead>

            <tbody>
              {flights.map((f, index) => (
                <tr key={index}>
                  <td>{f.name}</td>
                  <td>{f.flightCode}</td>
                  <td>{f.origin}</td>
                  <td>{f.destination}</td>
                  <td>{f.departureTime}</td>
                  <td>{f.arrivalTime}</td>
                  <td>₹{f.baseFare}</td>

                  <td>
                    {f.totalSeats <= 10 && f.totalSeats > 0 ? (
                      <span style={{ color: "red" }}>
                        Only {f.totalSeats} seats left
                      </span>
                    ) : (
                      f.totalSeats
                    )}
                  </td>

                  <td>
                    {f.totalSeats === 0 ? (
                      <button className="btn btn-secondary" disabled>
                        Full
                      </button>
                    ) : (
                      <button
                        className="btn btn-success"
                        onClick={() => selectFlight(f)}
                      >
                        Select
                      </button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        ))}

      {selectedFlight && (
        <div className="card p-3">
          <h5>Flight: {selectedFlight.name}</h5>
          <p>
            Route: {selectedFlight.origin} → {selectedFlight.destination}
          </p>

          <div className="mb-3">
            <label>Seats</label>
            <input
              type="number"
              min="1"
              max="6"
              className="form-control"
              value={seat}
              onChange={handleSeatChange}
            />
          </div>

          <h5>Total Fare: ₹{totalFare}</h5>

          <button className="btn btn-primary" onClick={confirmBooking}>
            Confirm Booking
          </button>
        </div>
      )}
    </div>
  );
};

export default AddBooking;