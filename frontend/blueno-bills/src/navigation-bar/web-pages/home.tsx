import React from "react";

const Home = () => {
  // websockets
  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Home Page!</h1>
          <ul id="userlist">USERS</ul>
        <div id="chat">CHAT</div>
        <script src="../../../../web-sockets/websocketDemo.js"/>
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
