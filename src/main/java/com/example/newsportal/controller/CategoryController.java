package com.example.newsportal.controller;

import com.example.newsportal.model.Category;
import com.example.newsportal.service.CategoryService;
import com.example.newsportal.service.impl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    //    CreateCategory page settings

    @GetMapping("/createCategory")
    public String createCategory(Model model) {
        model.addAttribute("newCategory", new Category());
        return "createCategory";
    }

    @PostMapping("/createCategory")
    public String createCategory(@ModelAttribute("newCategory") Category category) {
        categoryService.saveCategory(category);
        return "index";
    }
}
