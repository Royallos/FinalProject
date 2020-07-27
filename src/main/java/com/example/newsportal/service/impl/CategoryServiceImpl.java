package com.example.newsportal.service.impl;

import com.example.newsportal.model.Category;
import com.example.newsportal.repository.CategoryRepository;
import com.example.newsportal.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

}
