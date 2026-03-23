import { useNavigate } from "react-router-dom";
import { FaPlus, FaEye, FaSearch } from "react-icons/fa";
import "./BookingDashboard.css";

const BookingDashboard = () => {
  const navigate = useNavigate();

  const cards = [
    {
      title: "Add Booking",
      icon: <FaPlus />,
      gradient: "linear-gradient(135deg, #6C63FF, #9b59b6)",
      route: "/add-booking",
    },
    {
      title: "View Bookings",
      icon: <FaEye />,
      gradient: "linear-gradient(135deg, #28A745, #2ecc71)",
      route: "/display-bookings",
    },
    {
      title: "Search Booking",
      icon: <FaSearch />,
      gradient: "linear-gradient(135deg, #FFC107, #f39c12)",
      route: "/search-booking",
    },
  ];

  return (
    <div className="booking-dashboard">
      {/* Header */}
      <header className="dashboard-header">
        <h1>Booking Dashboard</h1>
        <p>Manage your flights and bookings efficiently</p>
      </header>

      {/* Cards */}
      <div className="dashboard-cards">
        {cards.map((card, idx) => (
          <div
            key={idx}
            className="dashboard-card"
            style={{ background: card.gradient }}
            onClick={() => navigate(card.route)}
          >
            <div className="card-icon">{card.icon}</div>
            <h5>{card.title}</h5>
          </div>
        ))}
      </div>
    </div>
  );
};

export default BookingDashboard;
