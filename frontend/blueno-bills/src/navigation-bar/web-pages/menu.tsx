import React from "react";

const Menu = () => {
  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Menu Page!</h1>
      </div>

      <div className={"page-buttons"}>
        <div className="ratty-button">
          <button>Sharpe Refectory</button>
        </div>

        <div className="vdub-button">
          <button>Verney Wooley</button>
        </div>

        <div className="andrews-button">
          <button>Andrews Commons</button>
        </div>
      </div>

      <div className={"page-buttons"}>
        <div className="ivy-button">
          <button>Ivy Room</button>
        </div>

        <div className="blue-button">
          <button>Blue Room</button>
        </div>

        <div className="joes-button">
          <button>Josiah's</button>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Menu;
