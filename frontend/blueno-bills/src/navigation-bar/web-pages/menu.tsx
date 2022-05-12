import React from "react";

const Menu = () => {
  const navigateToRattyMenus = () => {
    window.location.href = "/rattyMenus";
  };

  const navigateToVDubMenus = () => {
    window.location.href = "/vdubMenus";
  };

  const navigateToAndrewsMenus = () => {
    window.location.href = "/andrewsMenus";
  };

  const navigateToIvyRoomMenus = () => {
    window.location.href = "/ivyRoomMenus";
  };

  const navigateToBlueRoomMenus = () => {
    window.location.href = "/blueRoomMenus";
  };

  const navigateToJosMenus = () => {
    window.location.href = "/josMenus";
  };

  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Menu Page!</h1>
      </div>

      <div className={"page-buttons"}>
        <div className="ratty-button">
          <button onClick={navigateToRattyMenus}>Sharpe Refectory</button>
        </div>

        <div className="vdub-button">
          <button onClick={navigateToVDubMenus}>Verney Wooley</button>
        </div>

        <div className="andrews-button">
          <button onClick={navigateToAndrewsMenus}>Andrews Commons</button>
        </div>
      </div>

      <div className={"page-buttons"}>
        <div className="ivy-button">
          <button onClick={navigateToIvyRoomMenus}>Ivy Room</button>
        </div>

        <div className="blue-button">
          <button onClick={navigateToBlueRoomMenus}>Blue Room</button>
        </div>

        <div className="joes-button">
          <button onClick={navigateToJosMenus}>Josiah's</button>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Menu;
