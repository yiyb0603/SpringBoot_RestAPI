package com.example.demo.handler;

import com.example.demo.exception.CustomException;
import com.example.demo.exception.UnAuthorizationException;
import com.example.demo.lib.response.Response;
import com.example.demo.lib.response.ResponseData;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NullPointerException.class)
  public ResponseData<Response> handleNullPointerException(NullPointerException e) {
    e.printStackTrace();
    return new ResponseData<Response>(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류입니다.");
  }

  @ExceptionHandler(IOException.class)
  public ResponseData<Response> handleIOException(IOException e) {
    return new ResponseData<Response>(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류입니다.");
  }

  @ExceptionHandler(UnAuthorizationException.class)
  public ResponseData<Response> handleUnAuthorizationException(UnAuthorizationException e) {
    return new ResponseData<Response>(HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다.");
  }

  @ExceptionHandler(CustomException.class)
  public ResponseData<Response> handleCustomException(CustomException e) {
    return new ResponseData<Response>(e.getStatus(), e.getMessage());
  }
}