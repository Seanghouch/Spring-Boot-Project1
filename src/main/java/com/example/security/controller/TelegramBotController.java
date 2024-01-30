package com.example.security.controller;

import com.example.security.service.TelegramBotService;
import com.example.security.source.entity.Order;
import com.example.security.source.entity.TelegramBot;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/telegram")
public class TelegramBotController {

    @Autowired
    private TelegramBotService telegramBotService;

    @PostMapping(headers = "action=sendmessage")
    public Object sendMessage(@RequestBody TelegramBot telegramBot) throws JsonProcessingException {
//        Object data = telegramBotService.sendMessage(telegramBot);
        return null;
//        return "Telegram bot has been sent.";
    }

}
