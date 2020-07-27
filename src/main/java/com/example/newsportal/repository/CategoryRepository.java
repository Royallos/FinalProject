package com.example.newsportal.repository;

import com.example.newsportal.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("FROM Category cat where cat.name = ?1")
    Category findByName(String name);
}
