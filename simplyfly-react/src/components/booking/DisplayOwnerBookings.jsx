import React, { useEffect, useState } from "react";
import { getOwnerBookings } from "../services/BookingService";

function DisplayOwnerBookings() {

  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    fetchBookings();
  }, []);

  const fetchBookings = async () => {
    try {
      const response = await getOwnerBookings();
      setBookings(response.data);
    } catch (error) {
      console.error("Error fetching owner bookings:", error);
    }
  };

  return (
    <div>
      <h2>Bookings For My Flights</h2>

      <table border="1">
        <thead>
          <tr>
            <th>Booking ID</th>
            <th>Username</th>
            <th>Flight Code</th>
            <th>Seats</th>
            <th>Total Fare</th>
          </tr>
        </thead>

        <tbody>
          {bookings.map((b) => (
            <tr key={b.bookingId}>
              <td>{b.bookingId}</td>
              <td>{b.username}</td>
              <td>{b.flightCode}</td>
              <td>{b.seat}</td>
              <td>{b.totalFare}</td>
            </tr>
          ))}
        </tbody>

      </table>
    </div>
  );
}

export default DisplayOwnerBookings;