import axios from "axios";

const API_URL = "http://localhost:8081/api/flights";

const getAuthHeader = () => {
  const token = localStorage.getItem("token");

  if (!token) return {};

  return {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json"
  };
};

const FlightService = {

  getAllFlights: async () => {
    return axios.get(`${API_URL}/all`, {
      headers: getAuthHeader()
    });
  },

  getFlightByCode: async (code) => {
    return axios.get(`${API_URL}/byCode/${code}`, {
      headers: getAuthHeader()
    });
  },

  createFlight: async (addFlightDTO) => {
    return axios.post(`${API_URL}`, addFlightDTO, {
      headers: getAuthHeader()
    });
  },

  updateFlight: async (flightId, flightDTO) => {
    return axios.put(`${API_URL}/${flightId}`, flightDTO, {
      headers: getAuthHeader()
    });
  },

  deleteFlight: async (flightId) => {
    return axios.delete(`${API_URL}/${flightId}`, {
      headers: getAuthHeader()
    });
  }
};

export default FlightService;