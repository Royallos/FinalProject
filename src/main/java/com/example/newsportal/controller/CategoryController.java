package com.example.newsportal.controller;

import com.example.newsportal.model.Category;
import com.example.newsportal.model.Meeting;
import com.example.newsportal.model.User;
import com.example.newsportal.repository.CategoryRepository;
import com.example.newsportal.service.CategoryService;
import com.example.newsportal.service.impl.CategoryServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    private final CategoryServiceImpl categoryService;
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryServiceImpl categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    //    CreateCategory page settings

    @GetMapping("/createCategory")
    public String createCategory(Model model) {
        model.addAttribute("newCategory", new Category());
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "createCategory";
    }

    @PostMapping("/createCategory")
    public String createCategory(@ModelAttribute("newCategory") Category category, Model model) {
        categoryService.setCategory(category);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "showMeetings";
    }

    @GetMapping("/category/{id}")
    public String category(@PathVariable("id") Long id, Model model) {

        Category category = categoryRepository.findById(id).get();
        model.addAttribute("category", category);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "category";
    }
}
