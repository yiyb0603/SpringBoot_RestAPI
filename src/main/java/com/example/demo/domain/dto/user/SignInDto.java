package com.example.demo.domain.dto.user;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {
  @NotBlank
  private String id;

  @NotBlank
  private String password;
}
