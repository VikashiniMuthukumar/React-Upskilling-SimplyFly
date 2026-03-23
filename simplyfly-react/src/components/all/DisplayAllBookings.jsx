import { useEffect, useState } from "react";
import axios from "axios";
import BackButton from "../common/BackButton";

const DisplayAllBookings = () => {
  const [bookings, setBookings] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedBooking, setSelectedBooking] = useState(null);

  useEffect(() => {
    fetchBookings();
  }, []);

  const fetchBookings = async () => {
    try {
      const token = localStorage.getItem("token");

      const response = await axios.get("http://localhost:8081/api/bookings", {
        headers: token ? { Authorization: `Bearer ${token}` } : {},
      });

      setBookings(response.data);
    } catch (error) {
      console.error("Error fetching bookings:", error);
      setErrorMessage("Unable to fetch bookings");
    }
  };

  const deleteBooking = async (id) => {
    if (!window.confirm("Are you sure you want to delete this booking?")) return;

    try {
      const token = localStorage.getItem("token");
      await axios.delete(`http://localhost:8081/api/bookings/${id}`, {
        headers: token ? { Authorization: `Bearer ${token}` } : {},
      });

      setBookings(bookings.filter((b) => b.bookingId !== id));
    } catch (error) {
      alert("Delete failed");
    }
  };

  const openEditModal = (booking) => {
    setSelectedBooking({ ...booking });
    setShowEditModal(true);
  };

  const closeModal = () => {
    setShowEditModal(false);
    setSelectedBooking(null);
  };

  const updateBooking = async () => {
    try {
      const token = localStorage.getItem("token");
      await axios.put(
        `http://localhost:8081/api/bookings/${selectedBooking.bookingId}`,
        selectedBooking,
        { headers: token ? { Authorization: `Bearer ${token}` } : {} }
      );

      setBookings(
        bookings.map((b) =>
          b.bookingId === selectedBooking.bookingId ? selectedBooking : b
        )
      );
      closeModal();
    } catch (error) {
      alert("Update failed");
    }
  };

  return (
    <div className="container mt-4">
      {/* Reusable BackButton */}
      <BackButton title="All Bookings" backLink="/admin-dashboard" />

      {errorMessage && (
        <div className="alert alert-danger text-center">{errorMessage}</div>
      )}

      {bookings.length === 0 ? (
        <div className="alert alert-info text-center">No bookings found.</div>
      ) : (
        <div className="row">
          {bookings.map((booking) => (
            <div className="col-md-6 col-lg-4 mb-4" key={booking.bookingId}>
              <div className="card shadow-sm border rounded h-100">
                <div className="card-body">
                  <h5 className="card-title text-primary mb-2">
                    {booking.flightName ? booking.flightName.toUpperCase() : "UNKNOWN FLIGHT"}
                  </h5>
                  <p className="mb-1"><strong>Username:</strong> {booking.username}</p>
                  <p className="mb-1"><strong>Origin:</strong> {booking.origin}</p>
                  <p className="mb-1"><strong>Destination:</strong> {booking.destination}</p>
                  <p className="mb-1"><strong>Departure:</strong> {booking.departureTime}</p>
                  <p className="mb-1"><strong>Arrival:</strong> {booking.arrivalTime}</p>
                  <p className="mb-1"><strong>Seats:</strong> {booking.seat}</p>
                  <p className="mb-1"><strong>Total Fare (₹):</strong> {booking.totalFare}</p>
                  <p className="mb-1"><strong>Status:</strong> {booking.status}</p>
                  <p className="mb-1"><strong>Booked Date:</strong> {booking.bookDate}</p>

                  <div className="mt-3 d-flex justify-content-between">
                    <button
                      className="btn btn-sm btn-outline-primary"
                      onClick={() => openEditModal(booking)}
                    >
                      Edit
                    </button>
                    <button
                      className="btn btn-sm btn-outline-danger"
                      onClick={() => deleteBooking(booking.bookingId)}
                    >
                      Delete
                    </button>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}

      {/* Edit Modal */}
      {showEditModal && selectedBooking && (
        <div
          className="modal show d-block"
          style={{ background: "rgba(0,0,0,0.5)" }}
        >
          <div className="modal-dialog">
            <div className="modal-content rounded-3 shadow-sm">
              <div className="modal-header bg-light">
                <h5 className="modal-title text-dark">Edit Booking</h5>
                <button className="btn-close" onClick={closeModal}></button>
              </div>
              <div className="modal-body">
                <div className="form-group mb-2">
                  <label>Seats</label>
                  <input
                    type="number"
                    className="form-control"
                    min="1"
                    value={selectedBooking.seat}
                    onChange={(e) =>
                      setSelectedBooking({
                        ...selectedBooking,
                        seat: Number(e.target.value),
                        totalFare: selectedBooking.baseFare * Number(e.target.value),
                      })
                    }
                  />
                </div>

                <div className="form-group mb-2">
                  <label>Status</label>
                  <select
                    className="form-control"
                    value={selectedBooking.status}
                    onChange={(e) =>
                      setSelectedBooking({
                        ...selectedBooking,
                        status: e.target.value,
                      })
                    }
                  >
                    <option value="CONFIRMED">CONFIRMED</option>
                    <option value="CANCELLED">CANCELLED</option>
                  </select>
                </div>

                <div className="form-group mb-2">
                  <label>Total Fare (₹)</label>
                  <input
                    type="number"
                    className="form-control"
                    value={selectedBooking.totalFare}
                    readOnly
                  />
                </div>
              </div>
              <div className="modal-footer bg-light">
                <button className="btn btn-outline-success" onClick={updateBooking}>
                  Update
                </button>
                <button className="btn btn-outline-secondary" onClick={closeModal}>
                  Cancel
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default DisplayAllBookings;