package com.example.newsportal.repository;

import com.example.newsportal.model.Meeting;
import com.example.newsportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    @Query("FROM Meeting m where m.name = ?1")
    Meeting findByName(String Name);
}
