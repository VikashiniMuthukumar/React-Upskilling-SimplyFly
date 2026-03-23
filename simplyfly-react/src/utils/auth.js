// src/utils/auth.js
export const hasRole = (role) => {
  return localStorage.getItem("role") === role;
};