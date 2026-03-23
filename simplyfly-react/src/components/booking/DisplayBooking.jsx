import React, { useEffect, useState, useCallback } from "react";
import {
  getBookingsByUsername,
  updateBooking,
  deleteBooking,
} from "../../services/bookingService";

const DisplayBooking = () => {

  const username = localStorage.getItem("username");

  const [bookings, setBookings] = useState([]);
  const [editingBooking, setEditingBooking] = useState(null);
  const [editedSeats, setEditedSeats] = useState(1);

  /*
  LOAD BOOKINGS
  */

  const loadBookings = useCallback(() => {

    getBookingsByUsername(username)
      .then((res) => {
        setBookings(res.data);
      })
      .catch((err) => console.error("Error loading bookings", err));

  }, [username]);

  /*
  USE EFFECT
  */

  useEffect(() => {
    loadBookings();
  }, [loadBookings]);

  /*
  EDIT BOOKING
  */

  const handleEditClick = (booking) => {
    setEditingBooking(booking);
    setEditedSeats(booking.seat);
  };

  /*
  UPDATE BOOKING
  */

  const handleUpdateBooking = (booking) => {

    const updatedBooking = {
      ...booking,
      seat: editedSeats,
      totalFare: booking.baseFare * editedSeats,
    };

    updateBooking(booking.bookingId, updatedBooking)
      .then(() => {

        alert("Booking updated successfully");

        setEditingBooking(null);

        loadBookings();

      })
      .catch((err) => console.error("Update error", err));
  };

  /*
  CANCEL BOOKING
  */

  const handleCancelBooking = (bookingId) => {

    if (!window.confirm("Cancel this ticket?")) return;

    deleteBooking(bookingId)
      .then(() => {

        alert("Ticket cancelled successfully");

        loadBookings();

      })
      .catch((err) => console.error("Cancel error", err));
  };

  return (

    <div className="container mt-4">

      <h2 className="text-center mb-4">My Bookings</h2>

      <div className="row">

        {bookings.length > 0 ? (

          bookings.map((booking) => (

            <div key={booking.bookingId} className="col-md-6 col-lg-4 mb-4">

              <div className="card shadow-sm h-100">

                <div className="card-body">

                  <h5 className="fw-bold">
                    {booking.flightCode} - {booking.flightName}
                  </h5>

                  <p>
                    <strong>Route:</strong> {booking.origin} → {booking.destination}
                  </p>

                  <p>
                    <strong>Date:</strong> {booking.bookDate}
                  </p>

                  <p>
                    <strong>Status:</strong>{" "}
                    <span className={booking.status === "CANCELLED" ? "text-danger fw-bold" : "text-success fw-bold"}>
                      {booking.status}
                    </span>
                  </p>

                  <p>
                    <strong>Base Fare:</strong> ₹{booking.baseFare}
                  </p>

                  <p>
                    <strong>Total Fare:</strong>{" "}
                    {editingBooking?.bookingId === booking.bookingId
                      ? `₹${booking.baseFare * editedSeats}`
                      : `₹${booking.totalFare}`}
                  </p>

                  <p>
                    <strong>Seats:</strong>{" "}

                    {editingBooking?.bookingId === booking.bookingId ? (

                      <input
                        type="number"
                        className="form-control"
                        min="1"
                        max="6"
                        value={editedSeats}
                        onChange={(e) => setEditedSeats(Number(e.target.value))}
                      />

                    ) : (

                      booking.seat

                    )}

                  </p>

                  <div className="d-flex justify-content-between mt-3">

                    {booking.status !== "CANCELLED" && (

                      editingBooking?.bookingId === booking.bookingId ? (

                        <>
                          <button
                            className="btn btn-sm btn-success"
                            onClick={() => handleUpdateBooking(booking)}
                          >
                            Save
                          </button>

                          <button
                            className="btn btn-sm btn-secondary"
                            onClick={() => setEditingBooking(null)}
                          >
                            Cancel
                          </button>
                        </>

                      ) : (

                        <button
                          className="btn btn-sm btn-outline-primary"
                          onClick={() => handleEditClick(booking)}
                        >
                          Edit
                        </button>

                      )

                    )}

                    {booking.status !== "CANCELLED" && (

                      <button
                        className="btn btn-sm btn-outline-danger"
                        onClick={() => handleCancelBooking(booking.bookingId)}
                      >
                        Cancel Ticket
                      </button>

                    )}

                    {booking.status === "CANCELLED" && (

                      <span 
                      className="badge bg-danger align-self-center"
                      style={{ fontSize: "14px", padding: "8px 12px" }}
                      >
                        Cancelled
                      </span>

                    )}

                  </div>

                </div>

              </div>

            </div>

          ))

        ) : (

          <div className="alert alert-info text-center">
            No bookings found
          </div>

        )}

      </div>

    </div>

  );

};

export default DisplayBooking;