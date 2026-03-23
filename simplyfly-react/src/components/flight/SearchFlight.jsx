import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { getAllBookings } from "../../services/bookingService";

const SearchFlight = () => {

  const navigate = useNavigate();

  const [flights, setFlights] = useState([]);
  const [filteredFlights, setFilteredFlights] = useState([]);
  const [flightNames, setFlightNames] = useState([]);
  const [bookedSeats, setBookedSeats] = useState({});

  const [filters, setFilters] = useState({
    name: ""
  });

  const token = localStorage.getItem("token");

  const FLIGHT_URL = "http://localhost:8081/api/flights/all";


  useEffect(() => {

    const loadData = async () => {

      try {

        // 🔹 Get Flights
        const flightRes = await axios.get(FLIGHT_URL, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });

        const flightData = flightRes.data;

        setFlights(flightData);
        setFilteredFlights(flightData);

        
        const uniqueNames = [...new Set(flightData.map(f => f.name))];
        setFlightNames(uniqueNames);

       
        const bookingRes = await getAllBookings();
        const bookings = bookingRes.data;

        const seatMap = {};

        bookings.forEach(b => {

          if (!seatMap[b.flightCode]) {
            seatMap[b.flightCode] = 0;
          }

          seatMap[b.flightCode] += b.seat;

        });

        setBookedSeats(seatMap);

      } catch (error) {
        console.error("Error loading data:", error);
      }

    };

    loadData();

  }, [token]);


  const handleChange = (e) => {

    setFilters({
      ...filters,
      [e.target.name]: e.target.value
    });

  };

  
  const handleSearch = () => {

    let result = flights;

    if (filters.name !== "") {
      result = result.filter(f => f.name === filters.name);
    }

    setFilteredFlights(result);

  };

  return (

    <div className="container mt-4">

     
      <div className="d-flex justify-content-between align-items-center mb-3">

        <h3 className="text-primary fw-bold">
          🔍 Search Flights
        </h3>

        <button
          className="btn btn-outline-secondary"
          onClick={() => navigate(-1)}
        >
          ⬅ Back
        </button>

      </div>

      
      <div className="row g-3 mb-3">

        
        <div className="col-md-4">

          <select
            className="form-select"
            name="name"
            value={filters.name}
            onChange={handleChange}
          >

            <option value="">All Flights</option>

            {flightNames.map(name => (

              <option key={name} value={name}>
                {name}
              </option>

            ))}

          </select>

        </div>

      </div>

      <button
        className="btn btn-primary mb-3"
        onClick={handleSearch}
      >
        Search
      </button>

      
      <table className="table table-bordered text-center">

        <thead className="table-dark">

          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Flight Code</th>
            <th>Total Seats</th>
            <th>Booked Seats</th>
            <th>Available Seats</th>
            <th>Cabin Baggage</th>
            <th>Check-In Baggage</th>
          </tr>

        </thead>

        <tbody>

          {filteredFlights.length === 0 ? (

            <tr>
              <td colSpan="8" className="text-center">
                No flights found
              </td>
            </tr>

          ) : (

            filteredFlights.map(f => (

              <tr key={f.flightId}>

                <td>{f.flightId}</td>
                <td>{f.name}</td>
                <td>{f.flightCode}</td>

                <td>{f.totalSeats}</td>

                
                <td>{bookedSeats[f.flightCode] || 0}</td>

                
                <td>
                  {f.totalSeats - (bookedSeats[f.flightCode] || 0)}
                </td>

                <td>{f.cabinBaggageLimit} kg</td>
                <td>{f.checkInBaggageLimit} kg</td>

              </tr>

            ))

          )}

        </tbody>

      </table>

    </div>

  );

};

export default SearchFlight;