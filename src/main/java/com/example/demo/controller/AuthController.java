package com.example.demo.controller;

import java.util.HashMap;

import javax.validation.Valid;

import com.example.demo.domain.dto.user.SignInDto;
import com.example.demo.domain.dto.user.SignUpDto;
import com.example.demo.lib.response.Response;
import com.example.demo.lib.response.ResponseData;
import com.example.demo.service.auth.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/sign-up")
  public ResponseData<Response> handleSignUp(@Valid @RequestBody SignUpDto signUpDto) {
    authService.handleSignUp(signUpDto);
    return new ResponseData<Response>(HttpStatus.OK, "회원가입 성공");
  }

  @PostMapping("/sign-in")
  public ResponseData<HashMap<String, String>> handleSignIn(@Valid @RequestBody SignInDto signInDto) {
    HashMap<String, String> tokenMap = authService.handleSignIn(signInDto);
    return new ResponseData<HashMap<String, String>>(HttpStatus.OK, "로그인 성공", tokenMap);
  }
}
