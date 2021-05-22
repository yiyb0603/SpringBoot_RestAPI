package com.example.demo.service.user;

import java.util.HashMap;
import java.util.List;

import com.example.demo.domain.entity.Post;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.exception.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public User handleGetUser(String id) {
    User user = userRepository.findById(id);

    if (user == null) {
      throw new CustomException(HttpStatus.NOT_FOUND, "존재하지 않는 유저");
    }

    return user;
  }

  @Override
  public HashMap<String, List<Post>> handleGetUserPosts(String id) {
    User user = handleGetUser(id);

    HashMap<String, List<Post>> postsMap = new HashMap<>();
    postsMap.put("posts", user.getPosts());
    return postsMap;
  }
}
