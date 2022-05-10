let wsurl: string = "";
let webSocket: any = null;

//Send message if "Send" is clicked
id("send")!.addEventListener("click", function () {
  sendMessage(id("message")!.value);
});

//Send a message if it's not empty, then clear the input field
function sendMessage(message: string) {
  if (message !== "") {
    webSocket.send(message);
    id("message")!.value = "";
  }
}

//Update the chat-panel, and the list of connected users
function updateChat(msg: any) {
  var data = JSON.parse(msg.data);
  id("userlist").innerHTML = "";

  data.userlist.forEach(function (user: any) {
    insert("userlist", "<li>" + user + "</li>");
  });

  console.log("ITZY");
}

//Helper function for inserting HTML as the first child of an element
function insert(targetId: string, message: any) {
  id(targetId).insertAdjacentHTML("afterbegin", message);
}

//Helper function for selecting element by id
function id(id: string) {
  return document.getElementById(id) as HTMLInputElement;
}

export function logger(e: any) {
  console.log("AESPA");

  e.preventDefault();

  id("form").hidden = true;
  id("chatter").hidden = false;

  let username = id("username").value;

  //   wsurl =
  //     "ws://" +
  //     location.hostname +
  //     ":" +
  //     location.port +
  //     "/chat?username=" +
  //     username;

  wsurl = "/home";

  webSocket = new WebSocket(wsurl);
  webSocket.onmessage = function (msg: any) {
    updateChat(msg);
  };

  webSocket.onclose = function () {
    alert("WebSocket connection closed");
  };
}

export default updateChat;
