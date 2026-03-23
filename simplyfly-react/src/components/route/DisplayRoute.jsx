import React, { useEffect, useState } from "react";
import RouteService from "../../services/RouteService";
import FlightService from "../../services/FlightService";
import { useNavigate } from "react-router-dom";

const DisplayRoutes = () => {
  const navigate = useNavigate();

  const [routes, setRoutes] = useState([]);
  const [flightMap, setFlightMap] = useState({});
  const [errorMessage, setErrorMessage] = useState("");

  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedRoute, setSelectedRoute] = useState(null);

  useEffect(() => {
    loadRoutes();
    loadFlights();
  }, []);

  const loadRoutes = () => {
    RouteService.getRoutesByRole()
      .then(res => setRoutes(res.data))
      .catch(() => setErrorMessage("Unable to load routes"));
  };

  const loadFlights = () => {
    FlightService.getAllFlights()
      .then(res => {
        const map = {};
        res.data.forEach(f => {
          map[f.flightCode] = f.name;
        });
        setFlightMap(map);
      })
      .catch(err => console.error("Flight load failed", err));
  };

  const deleteRoute = (id) => {
    if (window.confirm("Are you sure you want to delete this route?")) {
      RouteService.deleteRoute(id)
        .then(() => loadRoutes())
        .catch(() => alert("Delete failed"));
    }
  };

  const openEditModal = (route) => {
    setSelectedRoute({ ...route });
    setShowEditModal(true);
  };

  const closeModal = () => {
    setShowEditModal(false);
    setSelectedRoute(null);
  };

  const updateRoute = (e) => {
    e.preventDefault();
    RouteService.updateRoute(selectedRoute.route_id, selectedRoute)
      .then(() => {
        closeModal();
        loadRoutes();
      })
      .catch(() => alert("Update failed"));
  };

  return (
    <div className="container mt-4">

      {/* ================= HEADER with Back Button ================= */}
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h3 className="text-primary fw-bold">🗺️ All Routes</h3>
        <button
          className="btn btn-outline-secondary"
          onClick={() => navigate(-1)}
        >
          ⬅ Back
        </button>
      </div>

      {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}

      <div className="row">
        {routes.length > 0 ? (
          routes.map(route => (
            <div key={route.route_id} className="col-md-6 col-lg-4 mb-4">
              <div className="card shadow-sm h-100">
                <div className="card-body">
                  <h5 className="fw-bold">
                    {route.origin} → {route.destination}
                  </h5>

                  <p><strong>Date:</strong> {route.scheduleDate}</p>
                  <p><strong>Departure:</strong> {route.departureTime}</p>
                  <p><strong>Arrival:</strong> {route.arrivalTime}</p>
                  <p><strong>Base Fare:</strong> ₹{route.baseFare}</p>

                  <p>
                    <strong>Flight:</strong>{" "}
                    {route.flightCode} - {flightMap[route.flightCode] || "—"}
                  </p>

                  <div className="d-flex justify-content-between mt-3">
                    <button
                      className="btn btn-sm btn-outline-secondary"
                      onClick={() => openEditModal(route)}
                    >
                      Edit
                    </button>

                    <button
                      className="btn btn-sm btn-outline-danger"
                      onClick={() => deleteRoute(route.route_id)}
                    >
                      Delete
                    </button>
                  </div>
                </div>
              </div>
            </div>
          ))
        ) : (
          <div className="alert alert-info text-center">No routes available</div>
        )}
      </div>

      {/* ================= EDIT MODAL ================= */}
      {showEditModal && selectedRoute && (
        <div className="modal fade show d-block" style={{ background: "rgba(0,0,0,0.5)" }}>
          <div className="modal-dialog">
            <div className="modal-content">
              <form onSubmit={updateRoute}>
                <div className="modal-header">
                  <h5>Edit Route</h5>
                  <button type="button" className="btn-close" onClick={closeModal}></button>
                </div>

                <div className="modal-body">
                  {/* READ ONLY FLIGHT */}
                  <label>Flight</label>
                  <input
                    className="form-control mb-2"
                    value={`${selectedRoute.flightCode} - ${flightMap[selectedRoute.flightCode] || ""}`}
                    readOnly
                  />

                  <label>Origin</label>
                  <input
                    className="form-control mb-2"
                    value={selectedRoute.origin}
                    onChange={e => setSelectedRoute({ ...selectedRoute, origin: e.target.value })}
                    required
                  />

                  <label>Destination</label>
                  <input
                    className="form-control mb-2"
                    value={selectedRoute.destination}
                    onChange={e => setSelectedRoute({ ...selectedRoute, destination: e.target.value })}
                    required
                  />

                  <label>Schedule Date</label>
<input
  type="date"
  className="form-control mb-2"
  value={selectedRoute.scheduleDate}
  min={new Date().toISOString().split("T")[0]} // today
  max={(() => {
    const d = new Date();
    d.setMonth(d.getMonth() + 3);
    return d.toISOString().split("T")[0];
  })()} // 3 months from now
  onChange={e =>
    setSelectedRoute({ ...selectedRoute, scheduleDate: e.target.value })
  }
  required
/>

                  <label>Departure Time</label>
                  <input
                    type="time"
                    className="form-control mb-2"
                    value={selectedRoute.departureTime}
                    onChange={e => setSelectedRoute({ ...selectedRoute, departureTime: e.target.value })}
                    required
                  />

                  <label>Arrival Time</label>
                  <input
                    type="time"
                    className="form-control mb-2"
                    value={selectedRoute.arrivalTime}
                    onChange={e => setSelectedRoute({ ...selectedRoute, arrivalTime: e.target.value })}
                    required
                  />

                  <label>Base Fare</label>
                  <input
                    type="number"
                    className="form-control"
                    value={selectedRoute.baseFare}
                    onChange={e => setSelectedRoute({ ...selectedRoute, baseFare: e.target.value })}
                    min="1"
                    required
                  />
                </div>

                <div className="modal-footer">
                  <button className="btn btn-success">Save</button>
                  <button type="button" className="btn btn-secondary" onClick={closeModal}>
                    Cancel
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default DisplayRoutes;