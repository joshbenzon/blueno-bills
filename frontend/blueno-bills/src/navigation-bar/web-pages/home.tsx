import React from "react";

const Home = () => {
  // update the list of connected users
  function addUser(message: MessageEvent) {
    var data = JSON.parse(message.data);
    getId("user-list").innerHTML = "";

    data.userlist.forEach(function (user: any) {
      insertUser("user-list", "<li>" + user + "</li>");
    });
  }

  // helper function for inserting HTML as the first child of an element
  function insertUser(targetId: string, message: any) {
    getId(targetId).insertAdjacentHTML("afterbegin", message);
  }

  // helper function for selecting element by id
  function getId(id: string) {
    return document.getElementById(id) as HTMLInputElement;
  }

  // creates the websocket from the user list
  const updateUserList = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    getId("form").hidden = true;
    getId("chatter").hidden = false;

    let username = localStorage.getItem("gmail");
    let webUrl = "ws://localhost:4567/home?username=" + username;
    let webSocket = new WebSocket(webUrl);

    webSocket.onmessage = function (msg: MessageEvent) {
      addUser(msg);
    };

    webSocket.onclose = function () {
      alert("WebSocket Connection Closed!");
    };

    return webUrl;
  };

  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Home Page!</h1>
      </div>

      <div className={"page-buttons"}>
        <div id="form">
          <form onSubmit={updateUserList}>
            <button>Check Active Users</button>
          </form>
        </div>

        <div id="chatter">
          <li id="user-list"></li>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Home;
