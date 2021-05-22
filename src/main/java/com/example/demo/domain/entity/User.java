package com.example.demo.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;

@Entity
@Table(name = "user")
@Getter
public class User {
  @Id
  @Column(nullable = false)
  private String id;

  @Column(nullable = false)
  @JsonIgnore
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
  private Date createdAt;

  @OneToMany(targetEntity = Post.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_user_id")
  @JsonIgnore
  private List<Post> posts;
}
