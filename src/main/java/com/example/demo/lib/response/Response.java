package com.example.demo.lib.response;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {
  HttpStatus status;
  String message;

  public Response(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }
}