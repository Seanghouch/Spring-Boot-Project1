package com.example.security.component;

import com.example.security.dto.response.ResponseData;
import com.example.security.service.TelegramBotService;
import com.example.security.source.entity.TelegramBot;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler{

    @Autowired
    private TelegramBotService telegramBotService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> error = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            error.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return error;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseData> handleAllException(Exception ex, WebRequest request, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        ResponseData responseData = new ResponseData(500, ex.getMessage(), request.getDescription(false));
        telegramBotService.sendMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                httpServletRequest.getMethod(),
                request.getDescription(false),
                ex.getLocalizedMessage(),
                ex.getMessage());
        return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({SignatureException.class})
    public ResponseEntity<ResponseData> handleJwtException(JwtException ex, WebRequest request){
        ResponseData responseData = new ResponseData(500, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(Exception.class)
//    public ProblemDetail handleSecurityException(Exception ex){
//        ProblemDetail errorDetail = null;
//        if(ex instanceof BadCredentialsException){
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
//            errorDetail.setProperty("access_denied_reason", "Authentication Failure");
//        }
//
//        if(ex instanceof AccessDeniedException){
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
//            errorDetail.setProperty("access_denied_reason", "not_authorized!");
//        }
//        return errorDetail;
//    }

}
