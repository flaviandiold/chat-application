package com.chat.cc_mini_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.cc_mini_project.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, String> {
}
