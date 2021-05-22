package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import com.example.demo.domain.entity.Post;
import com.example.demo.domain.entity.User;
import com.example.demo.lib.response.ResponseData;
import com.example.demo.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("{id}")
  public ResponseData<User> handleGetUser(@PathVariable("id") String id) {
    User user = userService.handleGetUser(id);
    return new ResponseData<User>(HttpStatus.OK, "유저 조회 성공", user);
  }

  @GetMapping("/posts/{id}")
  public ResponseData<HashMap<String, List<Post>>> handleGetUserPosts(@PathVariable("id") String id) {
    HashMap<String, List<Post>> postsMap = userService.handleGetUserPosts(id);
    return new ResponseData<HashMap<String, List<Post>>>(HttpStatus.OK, "유저 글 목록 조회 성공", postsMap);
  }
}
