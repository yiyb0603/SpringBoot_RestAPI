package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.domain.entity.User;
import com.example.demo.exception.UnAuthorizationException;
import com.example.demo.lib.Constants;
import com.example.demo.lib.Jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
  @Autowired
  private Jwt jwt;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader(Constants.HEADER_NAME);

    if (token != null && jwt.isAccessToken(token)) {
      User user = jwt.verifyToken(token);
      request.setAttribute("user", user);
      return true;
    } else {
      throw new UnAuthorizationException();
    }
  }
}
