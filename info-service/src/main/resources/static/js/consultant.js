function showConsultantButton() {
    document.getElementById("button").style.display="block";
}
setTimeout(showConsultantButton,5000);
let ws;
function init() {
    ws = new WebSocket("ws://localhost:9090/chat");
    ws.onopen = function (event) {
        const textarea = document.getElementById("messages");
        textarea.value = "Вас приветствует онлайн консультант" + "\n\n";
    }
    ws.onmessage = function(event) {
        const $textarea = document.getElementById("messages");
        $textarea.value = $textarea.value + event.data + "\n\n";
    };
    ws.onclose = function (event) {
    }
}
/*<button className="fc-close" tabIndex="0">
    <div className="fc-close-background"></div>
    <i className="material-icons fc-close-icon" translate="no">cancel</i></button>*/
function sendMessage() {
    const messageField = document.getElementById("message");
    ws.send(messageField.value);
    const textArea = document.getElementById("messages");
    textArea.value = textArea.value + "Вы: " + messageField.value + "\n";
    messageField.value = '';
    return false;
}
