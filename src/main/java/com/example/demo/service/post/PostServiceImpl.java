package com.example.demo.service.post;

import java.util.List;

import com.example.demo.domain.dto.post.PostDto;
import com.example.demo.domain.entity.Post;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.PostRepository;
import com.example.demo.exception.CustomException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
  @Autowired
  private PostRepository postRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public List<Post> handleGetPosts(int page, int limit) {
    Pageable pageable = PageRequest.of(page - 1, limit);
    Page<Post> posts = postRepository.findAll(pageable);

    return posts.getContent();
  }

  @Override
  public Post handleGetPost(int idx) {
    Post post = getExistPost(idx);
    return post;
  }

  @Override
  public void handleCreatePost(PostDto postDto) {
    Post post = modelMapper.map(postDto, Post.class);
    postRepository.save(post);
  }

  @Override
  public void handleModifyPost(int idx, PostDto postDto, User user) {
    Post existPost = getExistPost(idx);

    checkEqualUser(existPost.getUser(), user);

    modelMapper.map(postDto, existPost);
    postRepository.save(existPost);
  }

  @Override
  public void handleDeletePost(int idx, User user) {
    Post existPost = getExistPost(idx);

    checkEqualUser(existPost.getUser(), user);

    postRepository.delete(existPost);
  }

  public Post getExistPost(int idx) {
    Post existPost = postRepository.findByIdx(idx);

    if (existPost == null) {
      throw new CustomException(HttpStatus.NOT_FOUND, "존재하지 않는 글");
    }

    return existPost;
  }

  public void checkEqualUser(User postUser, User requestUser) {
    if (!(postUser.getId().equals(requestUser.getId()))) {
      throw new CustomException(HttpStatus.FORBIDDEN, "수정할 권한이 없습니다.");
    }
  }
}
