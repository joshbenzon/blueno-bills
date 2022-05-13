import React from "react";
import { useNavigate } from "react-router-dom";

const Transfer = () => {
  const navigate = useNavigate();

  const navigateToTransferMeal = () => {
    navigate("/transferMeal");
  };

  const navigateToTransferFlex = () => {
    navigate("/transferFlex");
  };

  const navigateToTransferBear = () => {
    navigate("/transferBear");
  };

  return (
    <React.Fragment>
      <div className="page-info">
        <h1>What Type of Payment to Transfer?</h1>
      </div>

      <div className={"page-buttons"}>
        <div className="transfer-meal-button">
          <button onClick={navigateToTransferMeal}>Meal Credits</button>
        </div>

        <div className="transfer-flex-button">
          <button onClick={navigateToTransferFlex}>Flex Points</button>
        </div>

        <div className="transfer-bear-button">
          <button onClick={navigateToTransferBear}>Bear Bucks</button>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Transfer;
