import axios from "axios";

const BASE_URL = "http://localhost:8081/api/bookings";



const authHeader = () => {
  const token = localStorage.getItem("token");

  return {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
};



export const addBooking = (booking) => {
  return axios.post(BASE_URL, booking, authHeader());
};



export const getBookingsByUsername = (username) => {
  return axios.get(`${BASE_URL}/user/${username}`, authHeader());
};


export const getAllBookings = () => {
  return axios.get(BASE_URL, authHeader());
};



export const updateBooking = (id, booking) => {
  return axios.put(`${BASE_URL}/${id}`, booking, authHeader());
};


export const deleteBooking = (id) => {
  return axios.delete(`${BASE_URL}/${id}`, authHeader());
};


export const searchBookingsByStatus = (status) => {
  return axios.get(`${BASE_URL}/status/${status}`, authHeader());
};

export const getOwnerBookings = () => {
  return axios.get(`${BASE_URL}/owner-bookings`, authHeader());
};


export const searchUserBookingsByStatus = (username, status) => {
  return axios.get(
    `${BASE_URL}/user/${username}/status/${status}`,
    authHeader()
  );
};

export const getTotalSeatsBooked = async (flightCode) => {
  const res = await axios.get(BASE_URL, authHeader());

  const bookings = res.data;

  const total = bookings
    .filter(b => b.flightCode === flightCode)
    .reduce((sum, b) => sum + b.seat, 0);

  return total;
};