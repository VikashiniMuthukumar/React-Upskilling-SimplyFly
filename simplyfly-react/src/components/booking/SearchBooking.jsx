import { useState } from "react";
import { searchUserBookingsByStatus } from "../../services/bookingService";
import BackButton from "../common/BackButton";

const SearchBooking = () => {

  const [status, setStatus] = useState("");
  const [results, setResults] = useState([]);

  const username = localStorage.getItem("username");

  const searchBooking = () => {

    if (!status) {
      alert("Select status");
      return;
    }

    searchUserBookingsByStatus(username, status)
      .then((res) => {
        setResults(res.data);
      })
      .catch((err) => {
        console.error("Search booking error:", err);
      });
  };

  return (
    <div className="container mt-4">

      <BackButton />

      <h4 className="mb-3">Search My Bookings</h4>

      <div className="d-flex gap-2 mb-3">

        <select
          className="form-control w-auto"
          value={status}
          onChange={(e) => setStatus(e.target.value)}
        >
          <option value="">Select Status</option>
          <option value="CONFIRMED">CONFIRMED</option>
          <option value="CANCELLED">CANCELLED</option>
        </select>

        <button
          className="btn btn-primary"
          onClick={searchBooking}
        >
          Search
        </button>

      </div>

      <div className="row">

        {results.length === 0 && (
          <p>No bookings found</p>
        )}

        {results.map((b) => (

          <div className="col-md-4 mb-3" key={b.bookingId}>

            <div className="card shadow-sm">

              <div className="card-body">

                <h5>
                  {b.flightCode} - {b.flightName}
                </h5>

                <p>
                  {b.origin} → {b.destination}
                </p>

                <p>
                  <strong>Seats:</strong> {b.seat}
                </p>

                <p>
                  <strong>Total Fare:</strong> ₹{b.totalFare}
                </p>

                <p>
                  <strong>Status:</strong>{" "}
                  <span
                    className={
                      b.status === "CANCELLED"
                        ? "text-danger"
                        : "text-success"
                    }
                  >
                    {b.status}
                  </span>
                </p>

              </div>

            </div>

          </div>

        ))}

      </div>

    </div>
  );
};

export default SearchBooking;