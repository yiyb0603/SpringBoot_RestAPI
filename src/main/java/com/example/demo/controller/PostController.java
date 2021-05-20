package com.example.demo.controller;

import com.example.demo.domain.dto.post.PostDto;
import com.example.demo.domain.entity.Post;
import com.example.demo.lib.response.Response;
import com.example.demo.lib.response.ResponseData;
import com.example.demo.service.post.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
public class PostController {
  @Autowired
  private PostService postService;

  @GetMapping
  public ResponseData<List<Post>> handleGetPosts(@RequestParam("page") int page, @RequestParam("limit") int limit) {
    List<Post> posts = postService.handleGetPosts(page, limit);
    return new ResponseData<List<Post>>(HttpStatus.OK, "글 목록 조회 성공", posts);
  }

  @GetMapping("{idx}")
  public ResponseData<Post> handleGetPost(@PathVariable("idx") int idx) {
    Post post = postService.handleGetPost(idx);
    return new ResponseData<Post>(HttpStatus.OK, "글 조회 성공", post);
  }

  @PostMapping
  public ResponseData<Response> handleCreatePost(@Valid @RequestBody PostDto postDto) {
    postService.handleCreatePost(postDto);
    return new ResponseData<Response>(HttpStatus.OK, "글 작성 성공");
  }

  @PutMapping("{idx}")
  public ResponseData<Response> handleModifyPost(@PathVariable("idx") int idx, @Valid @RequestBody PostDto postDto) {
    postService.handleModifyPost(idx, postDto);
    return new ResponseData<Response>(HttpStatus.OK, "글 수정 성공");
  }

  @DeleteMapping("{idx}")
  public ResponseData<Response> handleDeletePost(@PathVariable("idx") int idx) {
    postService.handleDeletePost(idx);
    return new ResponseData<Response>(HttpStatus.OK, "글 삭제 성공");
  }
}
