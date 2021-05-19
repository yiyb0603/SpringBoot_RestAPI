package com.example.demo.configuration;

import com.example.demo.lib.Jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfigutaion {
  @Bean
  public Jwt jwt() {
    return new Jwt();
  }
}
