package com.example.newsportal.service.impl;

import com.example.newsportal.model.Category;
import com.example.newsportal.model.Meeting;
import com.example.newsportal.model.User;
import com.example.newsportal.repository.CategoryRepository;
import com.example.newsportal.repository.UserRepository;
import com.example.newsportal.service.CategoryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void setCategory(Category category) {
        categoryRepository.save(category);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userRepository.findById(user.getId()).get().getCategories().add(category);
        userRepository.save(user);

    }

}
