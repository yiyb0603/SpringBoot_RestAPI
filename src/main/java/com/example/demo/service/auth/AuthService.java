package com.example.demo.service.auth;

import java.util.HashMap;

import com.example.demo.domain.dto.user.SignInDto;
import com.example.demo.domain.dto.user.SignUpDto;

public interface AuthService {
  public void handleSignUp(SignUpDto signUpDto);

  public HashMap<String, String> handleSignIn(SignInDto signInDto);
}
