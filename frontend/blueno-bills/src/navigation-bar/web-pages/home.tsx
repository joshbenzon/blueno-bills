import React from "react";
import { useForm } from "react-hook-form";

let wsurl: string = "";
let webSocket: any = null;

const Home = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (inputData: any) => console.log(inputData);

  //Send a message if it's not empty, then clear the input field
  function sendMessage(message: string) {
    if (message !== "") {
      webSocket.send(message);
      id("message")!.value = "";
    }
  }

  //Update the chat-panel, and the list of connected users
  function updateChat(msg: MessageEvent) {
    console.log("UPDATE CHAT");

    console.log(msg);
    var data = JSON.parse(msg.data);
    console.log(data);
    id("userlist").innerHTML = "";

    data.userlist.forEach(function (user: any) {
      console.log(user);
      insert("userlist", "<li>" + user + "</li>");
    });

    console.log("UPDATE CHATYY");
  }

  //Helper function for inserting HTML as the first child of an element
  function insert(targetId: string, message: any) {
    id(targetId).insertAdjacentHTML("afterbegin", message);
  }

  //Helper function for selecting element by id
  function id(id: string) {
    return document.getElementById(id) as HTMLInputElement;
  }

  const logger = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    id("form").hidden = true;
    id("chatter").hidden = false;

    let username = id("username").value;

    wsurl = "ws://localhost:4567/home?username=" + username;

    webSocket = new WebSocket(wsurl);

    webSocket.onmessage = function (msg: MessageEvent) {
      console.log("lol");
      updateChat(msg);
    };
    console.log("aespa");

    webSocket.webSocket.onclose = function () {
      console.log("oncliose");
      alert("WebSocket connection closed");
    };

    console.log("done");

    return wsurl;
  };

  return (
    <React.Fragment>
      <div className={"page-info"}>
        <h1>Home Page!</h1>
      </div>

      <div className={"page-buttons"}>
        <div id="form">
          {/* <form action="return logger(event)"> */}
          <form onSubmit={logger}>
            <input id="username" type="text" name="position" required></input>

            {/* <input type="submit"></input> */}
            {/* <button id="send" type="submit"> */}
            <button id="send">Submit!</button>
          </form>
        </div>

        <div id="chatter">
          <h2>Active Users</h2>
          <ul id="userlist"></ul>
          <div id="chat"></div>
        </div>

        {/* <script src="./websockets.tsx" /> */}
      </div>
    </React.Fragment>
  );
};

export default Home;
