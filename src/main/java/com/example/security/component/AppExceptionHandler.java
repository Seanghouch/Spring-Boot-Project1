package com.example.security.component;

import com.example.security.dto.response.ResponseData;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler{

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
    public ResponseEntity<ResponseData> handleAllException(Exception ex, WebRequest request){
        ResponseData responseData = new ResponseData(500, ex.getMessage(), request.getDescription(false));
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
