import { useEffect, useState } from "react";
import axios from "axios";
import BackButton from "../common/BackButton";

const DisplayAllUsers = () => {
  const [users, setUsers] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const token = localStorage.getItem("token");

      const response = await axios.get("http://localhost:8081/api/users", {
        headers: token ? { Authorization: `Bearer ${token}` } : {},
      });

      setUsers(response.data);
    } catch (error) {
      console.error("Error fetching users:", error);
      setErrorMessage("Unable to fetch users");
    }
  };

  const deleteUser = async (id) => {
    if (!window.confirm("Are you sure you want to delete this user?")) return;

    try {
      const token = localStorage.getItem("token");

      await axios.delete(`http://localhost:8081/api/users/${id}`, {
        headers: token ? { Authorization: `Bearer ${token}` } : {},
      });

      setUsers(users.filter((u) => u.id !== id));
    } catch (error) {
      alert("Delete failed");
    }
  };

  const openEditModal = (user) => {
    setSelectedUser({ ...user, password: "" }); // optional: reset password field
    setShowEditModal(true);
  };

  const closeModal = () => {
    setShowEditModal(false);
    setSelectedUser(null);
  };

  const updateUser = async () => {
    try {
      const token = localStorage.getItem("token");

      await axios.put(
        `http://localhost:8081/api/users/update/${selectedUser.username}`,
        selectedUser,
        { headers: token ? { Authorization: `Bearer ${token}` } : {} }
      );

      setUsers(
        users.map((u) =>
          u.id === selectedUser.id ? selectedUser : u
        )
      );
      closeModal();
    } catch (error) {
      alert("Update failed");
    }
  };

  return (
    <div className="container mt-4">
      <BackButton to="/admin-dashboard" />
      <h2 className="text-center mb-4">All Registered Users</h2>

      {errorMessage && (
        <div className="alert alert-danger text-center">{errorMessage}</div>
      )}

      {users.length === 0 ? (
        <div className="alert alert-info text-center">No users found.</div>
      ) : (
        <div className="row">
          {users.map((user) => (
            <div className="col-md-6 col-lg-4 mb-4" key={user.id}>
              <div className="card shadow-sm border rounded h-100">
                <div className="card-body">
                  <h5 className="card-title text-primary mb-2">
                    {user.username.toUpperCase()}
                  </h5>
                  <p className="mb-1">
                    <strong>User ID:</strong> {user.id}
                  </p>
                  <p className="mb-1">
                    <strong>Email:</strong> {user.email}
                  </p>
                  <p className="mb-1">
                    <strong>Role:</strong> {user.roles}
                  </p>

                  <div className="mt-3 d-flex justify-content-between">
                    <button
                      className="btn btn-sm btn-outline-primary"
                      onClick={() => openEditModal(user)}
                    >
                      Edit
                    </button>
                    <button
                      className="btn btn-sm btn-outline-danger"
                      onClick={() => deleteUser(user.id)}
                    >
                      Delete
                    </button>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}

      {/* Edit Modal */}
      {showEditModal && selectedUser && (
        <div
          className="modal show d-block"
          style={{ background: "rgba(0,0,0,0.5)" }}
        >
          <div className="modal-dialog">
            <div className="modal-content rounded-3 shadow-sm">
              <div className="modal-header bg-light">
                <h5 className="modal-title text-dark">Edit User</h5>
                <button className="btn-close" onClick={closeModal}></button>
              </div>
              <div className="modal-body">
                <div className="form-group mb-2">
                  <label>Username</label>
                  <input
                    type="text"
                    className="form-control"
                    value={selectedUser.username}
                    onChange={(e) =>
                      setSelectedUser({ ...selectedUser, username: e.target.value })
                    }
                  />
                </div>

                <div className="form-group mb-2">
                  <label>Email</label>
                  <input
                    type="email"
                    className="form-control"
                    value={selectedUser.email}
                    onChange={(e) =>
                      setSelectedUser({ ...selectedUser, email: e.target.value })
                    }
                  />
                </div>

                <div className="form-group mb-2">
                  <label>Password</label>
                  <input
                    type="password"
                    className="form-control"
                    value={selectedUser.password}
                    onChange={(e) =>
                      setSelectedUser({ ...selectedUser, password: e.target.value })
                    }
                  />
                </div>

                <div className="form-group mb-2">
                  <label>Role</label>
                  <input
                    type="text"
                    className="form-control"
                    value={selectedUser.roles}
                    onChange={(e) =>
                      setSelectedUser({ ...selectedUser, roles: e.target.value })
                    }
                  />
                </div>
              </div>
              <div className="modal-footer bg-light">
                <button className="btn btn-outline-success" onClick={updateUser}>
                  Update
                </button>
                <button className="btn btn-outline-secondary" onClick={closeModal}>
                  Cancel
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default DisplayAllUsers;