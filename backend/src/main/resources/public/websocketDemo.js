//Establish the WebSocket connection and set up event handlers
//let room = getRandomInt(0, 3);
//let username = "hari2";
let wsurl = null;
let webSocket = null;
console.log(wsurl);
console.log("hari4");

//Send message if "Send" is clicked
id("send").addEventListener("click", function () {
    sendMessage(id("message").value);
});

//Send a message if it's not empty, then clear the input field
function sendMessage(message) {
    if (message !== "") {
        webSocket.send(message);
        id("message").value = "";
    }
}

//Update the chat-panel, and the list of connected users
function updateChat(msg) {
    var data = JSON.parse(msg.data);
    id("userlist").innerHTML = "";
    data.userlist.forEach(function (user) {
        insert("userlist", "<li>" + user + "</li>");
    });
}

//Helper function for inserting HTML as the first child of an element
function insert(targetId, message) {
    id(targetId).insertAdjacentHTML("afterbegin", message);
}

//Helper function for selecting element by id
function id(id) {
    return document.getElementById(id);
}

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min) + min); //The maximum is exclusive and the minimum is inclusive
}

function logger(e) {
    e.preventDefault();
    id("form").hidden = true;
    id("chatter").hidden = false;
    console.log("submitted");
    let username = id("username").value;

    wsurl =
        "ws://" +
        location.hostname +
        ":" +
        location.port +
        "/chat?username=" +
        username;
    console.log(wsurl);
    webSocket = new WebSocket(wsurl);
    webSocket.onmessage = function (msg) {
        updateChat(msg);
    };

    webSocket.onclose = function () {
        alert("WebSocket connection closed");
    };
}
