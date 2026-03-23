import axios from "axios";

const API_URL = "http://localhost:8081/api/auth"; // login 
const USER_API_URL = "http://localhost:8081/api/users"; // registration 

const login = async ({ username, password }) => {
  const response = await axios.post(`${API_URL}/login`, { username, password });
  
  if (response.data && response.data.token) {
    localStorage.setItem("token", response.data.token);
    localStorage.setItem("roles", JSON.stringify(response.data.roles));
    localStorage.setItem("username", username);
  }

  return response.data;
};

const register = async (user) => {
  // send POST request to backend registration endpoint
  return axios.post(`${USER_API_URL}`, user);
};

const getCurrentUser = () => {
  const token = localStorage.getItem("token");
  const roles = JSON.parse(localStorage.getItem("roles") || "[]");
  const username = localStorage.getItem("username");
  return token ? { token, roles, username } : null;
};

const logout = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("roles");
  localStorage.removeItem("username");
};

export default { login, register, getCurrentUser, logout };