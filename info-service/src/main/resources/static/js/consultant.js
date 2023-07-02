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
        scrollToBottom();
    };
    ws.onclose = function (event) {
    }
}
function validateInput() {
    const messageField = document.getElementById("chat-input");
    if(messageField.value.at(0)===" ") messageField.value="";
}

function sendMessage() {
    const messageField = document.getElementById("chat-input");
    if(messageField.value==="" || messageField.value.at(0)===" ") return false;
    ws.send(messageField.value);
    const textArea = document.getElementById("textarea");
    textArea.innerHTML = textArea.innerHTML + "<p class='user-msg'>" + messageField.value + "</p>";
    messageField.value="";
    return false;
}
function scrollToBottom() {
    var textarea = document.getElementById("textarea");
    textarea.scrollTop = textarea.scrollHeight;
}


