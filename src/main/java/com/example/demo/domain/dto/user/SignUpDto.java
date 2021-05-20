package com.example.demo.domain.dto.user;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDto {
  @NotBlank
  private String id;

  @NotBlank
  private String password;

  @NotBlank
  private String name;

  @NotBlank
  private String email;
}
