package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Post;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
  public Post findByIdx(int idx);
}
