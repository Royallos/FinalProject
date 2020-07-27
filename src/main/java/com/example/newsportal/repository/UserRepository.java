package com.example.newsportal.repository;

import com.example.newsportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User us where us.userName = ?1")
    User findByUserName(String userName);
}
