package com.example.demo.domain.repository;

import com.example.demo.domain.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  public User findById(String id);

  public User findByIdAndPassword(String id, String password);
}
