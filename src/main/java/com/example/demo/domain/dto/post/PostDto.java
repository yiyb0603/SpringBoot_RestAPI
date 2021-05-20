package com.example.demo.domain.dto.post;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
  @NotBlank
  private String title;

  @NotBlank
  private String content;
}