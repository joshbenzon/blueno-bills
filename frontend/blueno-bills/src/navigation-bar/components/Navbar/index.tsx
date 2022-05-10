import { Nav, NavLink, Bars, NavMenu, NavBtn, NavBtnLink } from "./navigation";

import { useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();

  const navigateToLogin = () => {
    navigate("/menu");
  };

  return (
    <>
      <Nav>
        <NavLink to="/home">
          <img
            src={require("../../images/blueno-logo.png")}
            alt="blueno-bills-logo"
            width="75px"
          />
        </NavLink>

        <Bars />
        <NavMenu>
          <NavLink to="/request">Request</NavLink>
          <NavLink to="/transfer">Transfer</NavLink>
          <NavLink to="/menu">Menus</NavLink>
          <NavLink to="/account">Account</NavLink>
        </NavMenu>

        <NavBtn>
          <NavBtnLink to="/">Log Out</NavBtnLink>
        </NavBtn>
      </Nav>
    </>
  );
};

export default Navbar;
