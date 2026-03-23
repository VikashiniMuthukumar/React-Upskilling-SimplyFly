import axios from "axios";

const API_URL = "http://localhost:8081/api/routes";

const getAuthHeader = () => {
  const token = localStorage.getItem("token");

  return {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json"
  };
};

const RouteService = {

  // ADD ROUTE
  addRoute: (routeData) => {
    return axios.post(`${API_URL}/add`, routeData, {
      headers: getAuthHeader()
    });
  },

  // GET ALL ROUTES
  getAllRoutes: () => {
    return axios.get(`${API_URL}/getAll`, {
      headers: getAuthHeader()
    });
  },

  // GET ROUTES BY FLIGHT CODE
  getRoutesByFlightCode: (flightCode) => {
    return axios.get(`${API_URL}/flightCode/${flightCode}`, {
      headers: getAuthHeader()
    });
  },

  // GET ROUTE BY ID
  getRouteById: (routeId) => {
    return axios.get(`${API_URL}/${routeId}`, {
      headers: getAuthHeader()
    });
  },

  // UPDATE ROUTE
  updateRoute: (routeId, routeData) => {
    return axios.put(`${API_URL}/update/${routeId}`, routeData, {
      headers: getAuthHeader()
    });
  },

  // DELETE ROUTE
  deleteRoute: (routeId) => {
    return axios.delete(`${API_URL}/delete/${routeId}`, {
      headers: getAuthHeader()
    });
  },

  // GET ROUTE FLIGHT INFO
  getRouteFlightInfo: (origin, destination, flightCode) => {
    return axios.get(`${API_URL}/route-flight-info`, {
      params: {
        origin: origin,
        destination: destination,
        flightCode: flightCode
      },
      headers: getAuthHeader()
    });
  },

  // GET ROUTES BY DATE
  getRoutesByDate: (date) => {
    return axios.get(`${API_URL}/by-date/${date}`, {
      headers: getAuthHeader()
    });
  },

  // ROLE BASED ROUTES (ADMIN / OWNER)
  getRoutesByRole: () => {
    return axios.get(`${API_URL}/all`, {
      headers: getAuthHeader()
    });
  }

};

export default RouteService;