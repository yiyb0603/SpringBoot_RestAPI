package com.example.demo.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;

@Entity
@Table(name = "post")
@Getter
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column()
  private Integer idx;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_user_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  @CreationTimestamp()
  @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
  private Date createdAt;

  @UpdateTimestamp()
  @Column(name = "updated_at", nullable = true, columnDefinition = "TIMESTAMP DEFAULT NULL")
  private Date updatedAt = null;
}
