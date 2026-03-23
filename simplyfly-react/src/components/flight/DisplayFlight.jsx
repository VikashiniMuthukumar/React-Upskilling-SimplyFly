import React, { useEffect, useState } from "react";
import FlightService from "../../services/FlightService";
import BackButton from "../common/BackButton";

const DisplayFlight = () => {
  const [flights, setFlights] = useState([]);
  const [error, setError] = useState("");
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedFlight, setSelectedFlight] = useState(null);

  useEffect(() => {
    loadFlights();
  }, []);

  const loadFlights = () => {
    FlightService.getAllFlights()
      .then(res => setFlights(res.data))
      .catch(() => setError("Unable to load flights"));
  };

  const deleteFlight = (id) => {
    if (window.confirm("Are you sure you want to delete this flight?")) {
      FlightService.deleteFlight(id)
        .then(() => loadFlights())
        .catch(() => alert("Delete failed"));
    }
  };

  const openEditModal = (flight) => {
    setSelectedFlight({ ...flight });
    setShowEditModal(true);
  };

  const closeModal = () => {
    setShowEditModal(false);
    setSelectedFlight(null);
  };

  const updateFlight = (e) => {
    e.preventDefault();

    FlightService.updateFlight(selectedFlight.flightId, selectedFlight)
      .then(() => {
        closeModal();
        loadFlights();
      })
      .catch(() => alert("Update failed"));
  };

  return (
    <div className="container mt-4">
      <BackButton title="All Flights" backLink="/flight-dashboard" />

      {error && <div className="alert alert-danger">{error}</div>}

      <div className="row">
        {flights.length > 0 ? (
          flights.map(flight => (
            <div key={flight.flightId} className="col-md-6 col-lg-4 mb-4">
              <div className="card shadow-sm h-100">
                <div className="card-body">
                  <h5 className="fw-bold">{flight.name}</h5>
                  <p><strong>Flight Code:</strong> {flight.flightCode}</p>
                  <p><strong>Total Seats:</strong> {flight.totalSeats}</p>
                  <p><strong>Cabin Baggage:</strong> {flight.cabinBaggageLimit} kg</p>
                  <p><strong>Check-In Baggage:</strong> {flight.checkInBaggageLimit} kg</p>

                  <div className="d-flex justify-content-between mt-3">
                    <button className="btn btn-sm btn-outline-secondary" onClick={() => openEditModal(flight)}>Edit</button>
                    <button className="btn btn-sm btn-outline-danger" onClick={() => deleteFlight(flight.flightId)}>Delete</button>
                  </div>
                </div>
              </div>
            </div>
          ))
        ) : (
          <div className="alert alert-info text-center">No flights available</div>
        )}
      </div>

      {/* Edit Modal */}
      {showEditModal && selectedFlight && (
        <div className="modal fade show d-block" style={{ background: "rgba(0,0,0,0.5)" }}>
          <div className="modal-dialog">
            <div className="modal-content">
              <form onSubmit={updateFlight}>
                <div className="modal-header">
                  <h5>Edit Flight</h5>
                  <button type="button" className="btn-close" onClick={closeModal}></button>
                </div>

                <div className="modal-body">
                  <label>Flight Name</label>
                  <input className="form-control mb-2" value={selectedFlight.name} readOnly />

                  <label>Flight Code</label>
                  <input className="form-control mb-2" value={selectedFlight.flightCode} readOnly />

                  <label>Total Seats</label>
                  <input className="form-control mb-2" value={selectedFlight.totalSeats} readOnly />

                  <label>Cabin Baggage Limit (kg)</label>
                  <input
                    type="number"
                    className="form-control mb-2"
                    value={selectedFlight.cabinBaggageLimit}
                    max="7"
                    min="1"
                    onChange={e => setSelectedFlight({ ...selectedFlight, cabinBaggageLimit: e.target.value })}
                  />

                  <label>Check-In Baggage Limit (kg)</label>
                  <input
                    type="number"
                    className="form-control"
                    value={selectedFlight.checkInBaggageLimit}
                    max="20"
                    min="1"
                    onChange={e => setSelectedFlight({ ...selectedFlight, checkInBaggageLimit: e.target.value })}
                  />
                </div>

                <div className="modal-footer">
                  <button className="btn btn-success">Save</button>
                  <button type="button" className="btn btn-secondary" onClick={closeModal}>Cancel</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default DisplayFlight;