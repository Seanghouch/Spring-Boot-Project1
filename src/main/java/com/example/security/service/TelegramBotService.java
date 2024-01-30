package com.example.security.service;

import com.example.security.source.entity.Order;
import com.example.security.source.entity.TelegramBot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramBotService {

    private final RestClient restClient;
    @Value("${app.telegram.bot.token}")
    private String botToken;

    @Value("${app.telegram.bot.chatid}")
    private String chatId;

    public TelegramBotService(){
        restClient = RestClient.builder()
                .baseUrl("https://api.telegram.org")
                .build();
    }

    @Async("asyncTaskExecutor")
    public void sendMessage(String httpCode, String method, String uri, String request, String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String objectString;
        httpCode = ">Http Code: " + httpCode + "\n ";
        method = "Method: " + method + "\n ";
        uri = "Uri: `" + uri + "` \n ";
        request = "```request " + request + "``` ";
        response = "```response " + response + "```";
        objectString = httpCode + method + uri + request + response;
        System.out.println(objectString);
        Map<String, String> requestJson = new HashMap<>();
        requestJson.put("chat_id", chatId);
        requestJson.put("parse_mode", "MarkdownV2");
        requestJson.put("text", objectString);
        String requestString = objectMapper.writeValueAsString(requestJson);
        requestJson.clear();

        restClient.post()
                .uri("/bot"+botToken+"/sendMessage")
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestString)
                .retrieve()
                .body(new ParameterizedTypeReference<Object>(){});
    }


}
