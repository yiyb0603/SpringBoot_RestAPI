package com.example.demo.service.auth;

import java.util.HashMap;

import com.example.demo.domain.dto.user.SignInDto;
import com.example.demo.domain.dto.user.SignUpDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.exception.CustomException;
import com.example.demo.lib.Constants;
import com.example.demo.lib.Jwt;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    if (existUser != null) {
      throw new CustomException(HttpStatus.CONFLICT, "존재하는 아이디");
    }

    User user = modelMapper.map(signUpDto, User.class);
    userRepository.save(user);
  }

  @Override
  public HashMap<String, String> handleSignIn(SignInDto signInDto) {
    User existUser = userRepository.findByIdAndPassword(signInDto.getId(), signInDto.getPassword());

    if (existUser == null) {
      throw new CustomException(HttpStatus.UNAUTHORIZED, "올바르지 않은 아이디 또는 비밀번호");
    }

    HashMap<String, String> tokenMap = new HashMap<String, String>();
    String token = jwt.createToken(existUser);

    tokenMap.put(Constants.HEADER_NAME, token);
    return tokenMap;
  }
}
