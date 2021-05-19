package com.example.demo.lib;

import java.util.Calendar;
import java.util.HashMap;

import com.example.demo.domain.entity.User;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

public class Jwt {

  @Value("${jwt.secret.key}")
  private String jwtSecretKey;

  public String createToken(User user) {
    Calendar expiredAt = Calendar.getInstance();
    expiredAt.add(Calendar.DATE, Constants.TOKEN_EXPIRED);

    HashMap<String, Object> headerMap = new HashMap<String, Object>();

    headerMap.put("typ", "JWT");
    headerMap.put("alg", "HS256");

    HashMap<String, Object> claimsMap = new HashMap<String, Object>();

    claimsMap.put("id", user.getId());

    JwtBuilder builder = Jwts.builder().setHeaderParams(headerMap).setClaims(claimsMap)
        .setExpiration(expiredAt.getTime());

    return builder.compact();
  }
}
