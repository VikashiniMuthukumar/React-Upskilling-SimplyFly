const authHeader = () => {
  const token = localStorage.getItem("token");

  if (token && token !== "null" && token !== "undefined") {
    return {
      Authorization: `Bearer ${token}`,
    };
  }

  return {};
};

export default authHeader;