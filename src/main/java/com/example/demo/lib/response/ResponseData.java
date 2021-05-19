package com.example.demo.lib.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ResponseData<T> extends Response {
  private T data;

  public ResponseData(HttpStatus status, String message) {
    super(status, message);
  }

  public ResponseData(HttpStatus status, String message, T data) {
    super(status, message);
    this.data = data;
  }
}
