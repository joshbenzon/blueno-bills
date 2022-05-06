import React from "react";

const Request = () => {
  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>What Type of Payment to Request?</h1>
      </div>

      <div className={"page-buttons"}>
        <div className="request-meal-button">
          <button onClick={() => (window.location.href = "/requestMeal")}>
            Meal Credits
          </button>
        </div>

        <div className="request-flex-button">
          <button onClick={() => (window.location.href = "/requestFlex")}>
            Flex Points
          </button>
        </div>

        <div className="request-bear-button">
          <button onClick={() => (window.location.href = "/requestBear")}>
            Bear Bucks
          </button>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Request;
