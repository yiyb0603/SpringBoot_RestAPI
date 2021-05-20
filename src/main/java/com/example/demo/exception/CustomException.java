package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
  private HttpStatus status;
  private String message;

  public CustomException(HttpStatus status, String message) {
    super(message);

    this.status = status;
    this.message = message;
  }
}
