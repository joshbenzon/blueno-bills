<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>WebsSockets</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div id = "form">
    <form onsubmit="return logger(event)">
        Name<input id = "username" type="text" name="position" value="" required>
        <input type="submit" value="Enter Site">
    </form>
</div>

<div id = "chatter" hidden>
    <ul id="userlist"> <!-- Built by JS --> </ul>
    <span class="logged-in">●</span>
    <div id="chat">    <!-- Built by JS --> </div>
</div>
<script src="websocketDemo.js"></script>
</body>
</html>
