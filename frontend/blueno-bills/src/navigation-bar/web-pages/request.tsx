import React from "react";
import { useNavigate } from "react-router-dom";

const Request = () => {
  const navigate = useNavigate();

  const navigateToRequestMeal = () => {
    navigate("/requestMeal");
  };

  const navigateToRequestFlex = () => {
    navigate("/requestFlex");
  };

  const navigateToRequestBear = () => {
    navigate("/requestBear");
  };

  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>What Type of Payment to Request?</h1>
      </div>

      <div className={"page-buttons"}>
        <div className="request-meal-button">
          <button onClick={navigateToRequestMeal}>Meal Credits</button>
        </div>

        <div className="request-flex-button">
          <button onClick={navigateToRequestFlex}>Flex Points</button>
        </div>

        <div className="request-bear-button">
          <button onClick={navigateToRequestBear}>Bear Bucks</button>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Request;
