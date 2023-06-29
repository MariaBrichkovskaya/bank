package com.bank.infoservice.services;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final Map<String, String> responses = new HashMap<>();

    static {
        responses.put("привет", "Здравствуйте! Чем я могу вам помочь?");
        responses.put("баланс", "Ваш текущий баланс составляет 0 рублей нищеброд.");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        String response = responses.getOrDefault(request.toLowerCase(), "Извините, я не могу ответить на этот вопрос.");
        session.sendMessage(new TextMessage(response));
    }
}
