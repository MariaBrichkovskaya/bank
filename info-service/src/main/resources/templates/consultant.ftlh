<!DOCTYPE html>
<html lang="">
<head>
    <title>Диалоговое окно с консультантом банка</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>

        let ws;

        function init() {
            ws = new WebSocket("ws://localhost:9090/chat");
            ws.onopen = function (event) {
            }
            ws.onmessage = function(event) {
                const $textarea = document.getElementById("messages");
                $textarea.value = $textarea.value + event.data + "\n";
            };
            ws.onclose = function (event) {

            }
        }


        function sendMessage() {
            const messageField = document.getElementById("message");
            ws.send(messageField.value);
            messageField.value = '';
        }

    </script>
</head>
<body onload="init();">
<div id="dialog">


    <div id="chatbox">
        <textarea id="messages" rows="20" cols="50" readonly="readonly"></textarea>
    </div>

    <form name="message" action="">
        <input name="usermsg" type="text" id="message" size="40"/>
        <input type="button" name="submitmsg" value="Send..." onclick="sendMessage();"/>
    </form>
</div>
</body>
</html>
