import React from "react";
import "./Footer.css";

const Footer = () => {
  return (
    <footer className="app-footer">
      <div className="container text-center">

        <p className="mb-1">
          &copy; {new Date().getFullYear()} <strong>SimplyFly</strong>. All Rights Reserved.
        </p>

        <p className="mb-0">
          <a href="/terms" className="footer-link">Terms</a> |{" "}
          <a href="/privacy" className="footer-link">Privacy</a> |{" "}
          <a href="/contact" className="footer-link">Contact</a>
        </p>

      </div>
    </footer>
  );
};

export default Footer;