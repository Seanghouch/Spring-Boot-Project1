package com.example.security.source.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelegramBot {

    private String httpCode;
    private String method;
    private String uri;
    private Object request;
    private Object response;

}
