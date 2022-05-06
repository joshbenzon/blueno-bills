import React from "react";

const Home = () => {
  // websockets
  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Home Page!</h1>
      </div>

      <div>
        <ul id="userlist">NAME</ul>
        <div id="chat">CHAT</div>
        <script src="websocketDemo.js"/>
      </div>
    </React.Fragment>
  );

  // pure frontend
  // return (
  //   <React.Fragment>
  //     <div className={"page-info"}>
  //       <h1>Home Page!</h1>
  //     </div>
  //   </React.Fragment>
  // );
};

export default Home;
