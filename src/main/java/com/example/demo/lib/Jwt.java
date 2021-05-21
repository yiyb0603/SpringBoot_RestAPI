package com.example.demo.lib;

import java.security.Key;
import java.util.Calendar;
import java.util.HashMap;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.UnAuthorizationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

public class Jwt {
  @Value("${jwt.secret.key}")
  private String jwtSecretKey;

  @Autowired
  private UserRepository userRepository;

  private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

  public boolean isAccessToken(String jwtToken) {
    try {
      getClaims(jwtToken);
      return true;
    } catch (ExpiredJwtException e) {
      throw new CustomException(HttpStatus.GONE, "토큰이 만료되었습니다.");
    } catch (MalformedJwtException e) {
      throw new CustomException(HttpStatus.UNAUTHORIZED, "위조된 토큰입니다.");
    } catch (IllegalArgumentException e) {
      throw new CustomException(HttpStatus.BAD_REQUEST, "토큰이 전송되지 않았습니다.");
    } catch (Exception e) {
      e.printStackTrace();
      throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류입니다.");
    }
  }

  public String createToken(User user) {
    Calendar expiredAt = Calendar.getInstance();
    expiredAt.add(Calendar.DATE, Constants.TOKEN_EXPIRED);

    HashMap<String, Object> headerMap = new HashMap<String, Object>();

    headerMap.put("typ", "JWT");
    headerMap.put("alg", "HS256");

    HashMap<String, Object> claimsMap = new HashMap<String, Object>();
    claimsMap.put("id", user.getId());

    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    JwtBuilder builder = Jwts.builder();
    builder.setHeader(headerMap);
    builder.setClaims(claimsMap);
    builder.setExpiration(expiredAt.getTime());
    builder.signWith(signingKey, signatureAlgorithm);

    String jwtString = builder.compact();
    return jwtString;
  }

  private Claims getClaims(String jwtToken) {
    Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
        .parseClaimsJws(jwtToken).getBody();

    return claims;
  }

  public User verifyToken(String jwtToken) {
    Claims claims = getClaims(jwtToken);
    User user = userRepository.findById(String.valueOf(claims.get("id")));

    if (user == null) {
      throw new UnAuthorizationException();
    }

    return user;
  }
}
