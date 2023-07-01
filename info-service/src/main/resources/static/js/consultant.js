function showConsultantButton() {
    document.getElementById("chat-circle").style.display="block";
}
setTimeout(showConsultantButton,1000);
let ws;
function init() {
    ws = new WebSocket("ws://localhost:9090/chat");
    ws.onopen = function (event) {
        const textarea = document.getElementById("textarea");
        textarea.innerHTML = "<p class='consultant-msg'>Вас приветствует онлайн консультант</p>";
        //textarea.innerHTML="<p>Вас приветствует онлайн консультант</p>"
    }
    ws.onmessage = function(event) {
        const textarea = document.getElementById("textarea");
        textarea.innerHTML = textarea.innerHTML + "<p class='consultant-msg'>"+event.data+"</p>";
    };
    ws.onclose = function (event) {
    }
}
/*<button className="fc-close" tabIndex="0">
    <div className="fc-close-background"></div>
    <i className="material-icons fc-close-icon" translate="no">cancel</i></button>*/
function sendMessage() {
    const messageField = document.getElementById("chat-input");
    ws.send(messageField.value);
    const textArea = document.getElementById("textarea");
    textArea.innerHTML = textArea.innerHTML + "<p class='user-msg'>" + messageField.value + "</p>";
    messageField.value="";
    return false;
}
