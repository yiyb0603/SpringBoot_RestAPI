package com.example.demo.service.auth;

import java.util.HashMap;

import com.example.demo.domain.dto.user.SignInDto;
import com.example.demo.domain.dto.user.SignUpDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.lib.Jwt;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private Jwt jwt;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public void handleSignUp(SignUpDto signUpDto) {
    User existUser = userRepository.findById(signUpDto.getId());

    if (existUser == null) {
      // 중복 에러 처리
    }

    User user = modelMapper.map(signUpDto, User.class);
    userRepository.save(user);
  }

  @Override
  public HashMap<String, String> handleSignIn(SignInDto signInDto) {
    User existUser = userRepository.findById(signInDto.getId());

    HashMap<String, String> tokenMap = new HashMap<String, String>();
    String token = jwt.createToken(existUser);

    tokenMap.put("x-access-token", token);
    return tokenMap;
  }
}
