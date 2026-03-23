// import { useNavigate } from "react-router-dom";
// import { FaArrowLeft } from "react-icons/fa";

// // const BackButton = ({ title, backLink }) => {
// //   const navigate = useNavigate();

// //   return (
// //     <div className="d-flex justify-content-between align-items-center mb-3 w-100">
// //       {/* Title on the left */}
// //       <h3 className="text-primary fw-bold m-0">{title}</h3>

// //       {/* Back button on the right */}
// //       <button
// //         className="btn btn-outline-secondary d-flex align-items-center gap-2"
// //         onClick={() => navigate(backLink || -1)}
// //         style={{ zIndex: 10 }} // ensures it's above other elements
// //       >
// //         <FaArrowLeft />
// //         Back
// //       </button>
// //     </div>
// //   );
// // };

// const BackButton = ({ title }) => {
//   const navigate = useNavigate();

//   const role = localStorage.getItem("role");

//   const goBack = () => {
//     if (role === "ROLE_ADMIN") {
//       navigate("/admin-dashboard");
//     } else if (role === "ROLE_FLIGHT_OWNER") {
//       navigate("/flight-owner-dashboard");
//     } else {
//       navigate("/user-dashboard");
//     }
//   };

//   return (
//     <div className="d-flex justify-content-between align-items-center mb-3 w-100">
//       <h3 className="text-primary fw-bold m-0">{title}</h3>

//       <button
//         className="btn btn-outline-secondary d-flex align-items-center gap-2"
//         onClick={goBack}
//       >
//         Back
//       </button>
//     </div>
//   );
// };

// export default BackButton;

import { useNavigate } from "react-router-dom";
import { FaArrowLeft } from "react-icons/fa";

const BackButton = ({ title }) => {
  const navigate = useNavigate();
  const role = localStorage.getItem("role");

  const goBack = () => {

    if (role === "ROLE_ADMIN" || role === "ADMIN") {
      navigate("/admin-dashboard");

    } else if (role === "ROLE_FLIGHT_OWNER" || role === "FLIGHT_OWNER") {
      navigate("/flight-owner-dashboard");

    } else {
      navigate("/user-dashboard");
    }

  };

  return (
    <div className="d-flex justify-content-between align-items-center mb-3 w-100">

      <h3 className="text-primary fw-bold m-0">{title}</h3>

      <button
        className="btn btn-outline-secondary d-flex align-items-center gap-2"
        onClick={goBack}
      >
        <FaArrowLeft />
        Back
      </button>

    </div>
  );
};

export default BackButton;