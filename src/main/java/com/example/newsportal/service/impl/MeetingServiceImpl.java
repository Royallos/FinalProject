package com.example.newsportal.service.impl;

import com.example.newsportal.model.Meeting;
import com.example.newsportal.model.User;
import com.example.newsportal.repository.CategoryRepository;
import com.example.newsportal.repository.MeetingRepository;
import com.example.newsportal.repository.UserRepository;
import com.example.newsportal.service.MeetingService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService{

    private final MeetingRepository meetingRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public MeetingServiceImpl(MeetingRepository meetingRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void setMeeting(Meeting meeting, String category) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        meeting.getCategories().add(categoryRepository.findByName(category));
        meetingRepository.save(meeting);
        userRepository.findById(user.getId()).get().getMeetings().add(meeting);
        userRepository.save(user);

    }
}
