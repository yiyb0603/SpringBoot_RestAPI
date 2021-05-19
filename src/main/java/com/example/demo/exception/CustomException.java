package com.example.demo.exception;

public class CustomException extends RuntimeException {
  int status;
  String message;

  public CustomException(int status, String message) {
    super(message);

    this.status = status;
    this.message = message;
  }
}
