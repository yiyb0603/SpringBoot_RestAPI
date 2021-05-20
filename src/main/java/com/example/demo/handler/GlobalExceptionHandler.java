package com.example.demo.handler;

import com.example.demo.exception.CustomException;
import com.example.demo.lib.response.Response;
import com.example.demo.lib.response.ResponseData;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NullPointerException.class)
  public ResponseData<Response> handleNullPointerException(NullPointerException e) {
    return new ResponseData<Response>(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류입니다.");
  }

  @ExceptionHandler(UnAuthorizationException.class)
  public ResponseData<Response> handleUnAuthorizationException(UnAuthorizationException e) {
    return new ResponseData<Response>(HttpStatus.UNAUTHORIZED, "올바르지 않은 토큰입니다.");
  }

  @ExceptionHandler(ExpiredJwtException.class)
  public Response handleExpiredJwtException(ExpiredJwtException e) {
    return new ResponseData<Response>(HttpStatus.GONE, "토큰이 만료되었습니다.");
  }

  @ExceptionHandler(MalformedJwtException.class)
  public Response handleMalformedJwtException(MalformedJwtException e) {
    return new ResponseData<Response>(HttpStatus.UNAUTHORIZED, "위조된 토큰입니다.");
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public Response handleIllegalArgumentException(IllegalArgumentException e) {
    return new ResponseData<Response>(HttpStatus.UNAUTHORIZED, "토큰이 없습니다.");
  }

  @ExceptionHandler(CustomException.class)
  public ResponseData<Response> handleCustomException(CustomException e) {
    return new ResponseData<Response>(e.getStatus(), e.getMessage());
  }
}