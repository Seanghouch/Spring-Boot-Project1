package com.example.security.controller;

import com.example.security.dto.response.ResponseData;
import com.example.security.service.EmailSenderService;
import com.example.security.source.entity.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/send-email")
public class EmailSenderController {

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping(headers = "action=send")
    public ResponseEntity<Object> sendEmail(@RequestBody EmailMessage emailMessage){
        emailSenderService.sendEmail(emailMessage.getMailTo(), emailMessage.getSubject(), emailMessage.getMessage());
        return ResponseEntity.ok(new ResponseData(200, "Email has been sent.", null));
    }

}
