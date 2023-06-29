<!DOCTYPE html>
<html>
<head>
    <title>Диалоговое окно с консультантом банка</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>

        var socket = new WebSocket("ws://localhost:9090/chat");

        socket.onopen = function(event) {
            console.log("Соединение установлено");
        };

        socket.onmessage = function(event) {
            var response = event.data;
            // Отобразить ответ консультанта в диалоговом окне
            $("#dialog").append("<p>" + response + "</p>");
        };

        function sendMessage() {
            var request = $("#message").val();
            // Отправить введенное сообщение на сервер
            socket.send(request);
            // Отобразить введенное сообщение в диалоговом окне
            $("#dialog").append("<p><b>Вы:</b> " + request + "</p>");
            // Очистить поле ввода
            $("#message").val("");
        }
    </script>
</head>
<body>
<div id="dialog"></div>
<input type="text" id="message" placeholder="Введите сообщение">
<button onclick="sendMessage()">Отправить</button>
</body>
</html>
