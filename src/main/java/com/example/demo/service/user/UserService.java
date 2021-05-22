package com.example.demo.service.user;

import java.util.HashMap;
import java.util.List;

import com.example.demo.domain.entity.Post;
import com.example.demo.domain.entity.User;

public interface UserService {
  public User handleGetUser(String id);

  public HashMap<String, List<Post>> handleGetUserPosts(String id);
}
