package com.example.demo.service.post;

import com.example.demo.domain.dto.post.PostDto;
import com.example.demo.domain.entity.Post;
import com.example.demo.domain.entity.User;

import java.util.List;

public interface PostService {
  public List<Post> handleGetPosts(int page, int limit);

  public Post handleGetPost(int idx);

  public void handleCreatePost(PostDto postDto);

  public void handleModifyPost(int idx, PostDto postDto, User user);

  public void handleDeletePost(int idx, User user);
}
