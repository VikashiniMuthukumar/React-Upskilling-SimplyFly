// import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

// // layout
// import Navbar from "./components/default-nav/Navbar";
// import Footer from "./components/footer/Footer";

// // // auth
// // import Login from "./components/auth/Login";
// // import Register from "./components/auth/Register";

// // home
// import Home from "./pages/home/Home";

// // dashboards
// import AdminDashboard from "./components/dashboard/AdminDashboard";
// import UserDashboard from "./components/dashboard/UserDashboard";
// import FlightOwnerDashboard from "./components/dashboard/FlightOwnerDashboard";

// // booking
// import BookingDashboard from "./components/booking/BookingDashboard";
// import AddBooking from "./components/booking/AddBooking";
// import DisplayBooking from "./components/booking/DisplayBooking";
// import SearchBooking from "./components/booking/SearchBooking";

// // user
// import DisplayAllUsers from "./components/all/DisplayAllUsers";
// import EditUser from "./components/user/EditUser";

// // flight
// import AddFlight from "./components/flight/AddFlight";
// import DisplayFlight from "./components/flight/DisplayFlight";
// import SearchFlight from "./components/flight/SearchFlight";

// // route
// import RouteDashboard from "./components/route/RouteDashboard";
// import AddRoute from "./components/route/AddRoute";
// import DisplayRoute from "./components/route/DisplayRoute";
// import SearchRoute from "./components/route/SearchRoute";
// import FlightDashboard from "./components/flight/FlightDashboard";

// //all
// import DisplayAllBookings from "./components/all/DisplayAllBookings";


// import Login from "./components/auth/Login";
// import Register from "./components/auth/Register";
// import { hasRole } from "./utils/auth";

// // const UserDashboard = () => <h2>User Dashboard</h2>;
// // const AdminDashboard = () => <h2>Admin Dashboard</h2>;
// // const FlightOwnerDashboard = () => <h2>Flight Owner Dashboard</h2>;

// function App() {
//   return (
//     <BrowserRouter>
//       <Navbar />

//       <Routes>
//         {/* Auth */}
//         <Route path="/login" element={<Login />} />
//         <Route path="/register" element={<Register />} />

//         {/* Home */}
//         <Route path="/" element={<Home />} />

//         {/* Dashboards */}
//         <Route path="/admin-dashboard" element={<AdminDashboard />} />
//         <Route path="/user-dashboard" element={<UserDashboard />} />
//         <Route path="/flight-owner-dashboard" element={<FlightOwnerDashboard />} />

//         {/* Booking */}
//         <Route path="/booking-dashboard" element={<BookingDashboard />} />
//         <Route path="/add-booking" element={<AddBooking />} />
//         <Route path="/display-bookings" element={<DisplayBooking />} />
//         <Route path="/search-booking" element={<SearchBooking />} />

//         {/* User */}
//         <Route path="/display-users" element={<DisplayAllUsers />} />
//         <Route path="/edit-user" element={<EditUser />} />

//         {/* Flight */}
//          <Route path="/flight-dashboard" element={<FlightDashboard />} />
//         <Route path="/add-flight" element={<AddFlight />} />
//         <Route path="/display-flights" element={<DisplayFlight />} />
//         {/* <Route path="/search-flight" element={<SearchFlight />} /> */}

//         {/* Route */}
//         <Route path="/route-dashboard" element={<RouteDashboard />} />
//         <Route path="/add-route" element={<AddRoute />} />
//         <Route path="/display-routes" element={<DisplayRoute />} />
//         <Route path="/search-route" element={<SearchRoute />} />

//         {/* All */}
//         <Route path="/display-all-bookings" element={<DisplayAllBookings/>}/>
//         <Route path="/display-all-users" element={<DisplayAllUsers/>}/>

//         {/* Fallback */}

        
//         <Route path="*" element={<Navigate to="/login" />} />
     

//         <Route path="/" element={<Login />} />
//         <Route path="/login" element={<Login />} />
//         <Route path="/register" element={<Register />} />

//         <Route
//           path="/user/dashboard"
//           element={hasRole("ROLE_USER") ? <UserDashboard /> : <Navigate to="/login" />}
//         />
//         <Route
//           path="/admin/dashboard"
//           element={hasRole("ROLE_ADMIN") ? <AdminDashboard /> : <Navigate to="/login" />}
//         />
//         <Route
//           path="/owner/dashboard"
//           element={hasRole("ROLE_FLIGHT_OWNER") ? <FlightOwnerDashboard /> : <Navigate to="/login" />}
//         />
//       </Routes>

//       <Footer />
//     </BrowserRouter>
//   );
// }

// export default App;




// import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

// // layout
// import Navbar from "./components/default-nav/Navbar";
// import Footer from "./components/footer/Footer";

// // // auth
// // import Login from "./components/auth/Login";
// // import Register from "./components/auth/Register";

// // home
// import Home from "./pages/home/Home";

// // dashboards
// import AdminDashboard from "./components/dashboard/AdminDashboard";
// import UserDashboard from "./components/dashboard/UserDashboard";
// import FlightOwnerDashboard from "./components/dashboard/FlightOwnerDashboard";

// // booking
// import BookingDashboard from "./components/booking/BookingDashboard";
// import AddBooking from "./components/booking/AddBooking";
// import DisplayBooking from "./components/booking/DisplayBooking";
// import SearchBooking from "./components/booking/SearchBooking";

// // user
// import DisplayAllUsers from "./components/all/DisplayAllUsers";
// import EditUser from "./components/user/EditUser";

// // flight
// import AddFlight from "./components/flight/AddFlight";
// import DisplayFlight from "./components/flight/DisplayFlight";
// import SearchFlight from "./components/flight/SearchFlight";

// // route
// import RouteDashboard from "./components/route/RouteDashboard";
// import AddRoute from "./components/route/AddRoute";
// import DisplayRoute from "./components/route/DisplayRoute";
// import SearchRoute from "./components/route/SearchRoute";
// import FlightDashboard from "./components/flight/FlightDashboard";

// //all
// import DisplayAllBookings from "./components/all/DisplayAllBookings";


// import Login from "./components/auth/Login";
// import Register from "./components/auth/Register";
// import { hasRole } from "./utils/auth";

// // const UserDashboard = () => <h2>User Dashboard</h2>;
// // const AdminDashboard = () => <h2>Admin Dashboard</h2>;
// // const FlightOwnerDashboard = () => <h2>Flight Owner Dashboard</h2>;

// function App() {
//   return (
//     <>
//       <Navbar />

//       <Routes>
//         {/* Auth */}
//         <Route path="/login" element={<Login />} />
//         <Route path="/register" element={<Register />} />

//         {/* Home */}
//         <Route path="/" element={<Home />} />

//         {/* Dashboards */}
//         <Route path="/admin-dashboard" element={<AdminDashboard />} />
//         <Route path="/user-dashboard" element={<UserDashboard />} />
//         <Route path="/flight-owner-dashboard" element={<FlightOwnerDashboard />} />

//         {/* Booking */}
//         <Route path="/booking-dashboard" element={<BookingDashboard />} />
//         <Route path="/add-booking" element={<AddBooking />} />
//         <Route path="/display-bookings" element={<DisplayBooking />} />
//         <Route path="/search-booking" element={<SearchBooking />} />

//         {/* User */}
//         <Route path="/display-users" element={<DisplayAllUsers />} />
//         <Route path="/edit-user" element={<EditUser />} />

//         {/* Flight */}
//          <Route path="/flight-dashboard" element={<FlightDashboard />} />
//         <Route path="/add-flight" element={<AddFlight />} />
//         <Route path="/display-flights" element={<DisplayFlight />} />
//         <Route path="/search-flight" element={<SearchFlight />} />

//         {/* Route */}
//         <Route path="/route-dashboard" element={<RouteDashboard />} />
//         <Route path="/add-route" element={<AddRoute />} />
//         <Route path="/display-routes" element={<DisplayRoute />} />
//         <Route path="/search-route" element={<SearchRoute />} />

//         {/* All */}
//         <Route path="/display-all-bookings" element={<DisplayAllBookings/>}/>
//         <Route path="/display-all-users" element={<DisplayAllUsers/>}/>

//         {/* Fallback */}

        
//         <Route path="*" element={<Navigate to="/login" />} />
     

//         <Route path="/" element={<Login />} />
//         <Route path="/login" element={<Login />} />
//         <Route path="/register" element={<Register />} />

//         <Route
//           path="/user/dashboard"
//           element={hasRole("ROLE_USER") ? <UserDashboard /> : <Navigate to="/login" />}
//         />
//         <Route
//           path="/admin/dashboard"
//           element={hasRole("ROLE_ADMIN") ? <AdminDashboard /> : <Navigate to="/login" />}
//         />
//         <Route
//           path="/owner/dashboard"
//           element={hasRole("ROLE_FLIGHT_OWNER") ? <FlightOwnerDashboard /> : <Navigate to="/login" />}
//         />
//       </Routes>

//       <Footer />
//     </>
//   );
// }

// export default App;




import { Routes, Route, Navigate } from "react-router-dom";

// layout
import Navbar from "./components/default-nav/Navbar";
import Footer from "./components/footer/Footer";

// auth
import Login from "./components/auth/Login";
import Register from "./components/auth/Register";

// home
import Home from "./pages/home/Home";

// dashboards
import AdminDashboard from "./components/dashboard/AdminDashboard";
import UserDashboard from "./components/dashboard/UserDashboard";
import FlightOwnerDashboard from "./components/dashboard/FlightOwnerDashboard";

// booking
import BookingDashboard from "./components/booking/BookingDashboard";
import AddBooking from "./components/booking/AddBooking";
import DisplayBooking from "./components/booking/DisplayBooking";
import SearchBooking from "./components/booking/SearchBooking";

// user
import DisplayAllUsers from "./components/all/DisplayAllUsers";
import EditUser from "./components/user/EditUser";

// flight
import AddFlight from "./components/flight/AddFlight";
import DisplayFlight from "./components/flight/DisplayFlight";
import SearchFlight from "./components/flight/SearchFlight";
import FlightDashboard from "./components/flight/FlightDashboard";

// route
import RouteDashboard from "./components/route/RouteDashboard";
import AddRoute from "./components/route/AddRoute";
import DisplayRoute from "./components/route/DisplayRoute";
import SearchRoute from "./components/route/SearchRoute";

// all
import DisplayAllBookings from "./components/all/DisplayAllBookings";

import { hasRole } from "./utils/auth";

function App() {
  return (
    <div className="app-container">

      <Navbar />

      <main className="content">
        <Routes>

          {/* Auth */}
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />

          {/* Home */}
          <Route path="/" element={<Home />} />

          {/* Dashboards */}
          <Route path="/admin-dashboard" element={<AdminDashboard />} />
          <Route path="/user-dashboard" element={<UserDashboard />} />
          <Route path="/flight-owner-dashboard" element={<FlightOwnerDashboard />} />

          {/* Booking */}
          <Route path="/booking-dashboard" element={<BookingDashboard />} />
          <Route path="/add-booking" element={<AddBooking />} />
          <Route path="/display-bookings" element={<DisplayBooking />} />
          <Route path="/search-booking" element={<SearchBooking />} />

          {/* User */}
          <Route path="/display-users" element={<DisplayAllUsers />} />
          <Route path="/edit-user" element={<EditUser />} />

          {/* Flight */}
          <Route path="/flight-dashboard" element={<FlightDashboard />} />
          <Route path="/add-flight" element={<AddFlight />} />
          <Route path="/display-flights" element={<DisplayFlight />} />
          <Route path="/search-flight" element={<SearchFlight />} />

          {/* Route */}
          <Route path="/route-dashboard" element={<RouteDashboard />} />
          <Route path="/add-route" element={<AddRoute />} />
          <Route path="/display-routes" element={<DisplayRoute />} />
          <Route path="/search-route" element={<SearchRoute />} />

          {/* Admin views */}
          <Route path="/display-all-bookings" element={<DisplayAllBookings />} />
          <Route path="/display-all-users" element={<DisplayAllUsers />} />

          {/* Role protected routes */}
          <Route
            path="/user/dashboard"
            element={hasRole("ROLE_USER") ? <UserDashboard /> : <Navigate to="/login" />}
          />

          <Route
            path="/admin/dashboard"
            element={hasRole("ROLE_ADMIN") ? <AdminDashboard /> : <Navigate to="/login" />}
          />

          <Route
            path="/owner/dashboard"
            element={hasRole("ROLE_FLIGHT_OWNER") ? <FlightOwnerDashboard /> : <Navigate to="/login" />}
          />

          {/* Fallback */}
          <Route path="*" element={<Navigate to="/login" />} />

        </Routes>
      </main>

      <Footer />

    </div>
  );
}

export default App;