package com.example.newsportal.service.impl;

import com.example.newsportal.model.Meeting;
import com.example.newsportal.model.User;
import com.example.newsportal.repository.CategoryRepository;
import com.example.newsportal.repository.MeetingRepository;
import com.example.newsportal.service.MeetingService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService{

    private final MeetingRepository meetingRepository;
    private final CategoryRepository categoryRepository;

    public MeetingServiceImpl(MeetingRepository meetingRepository, CategoryRepository categoryRepository) {
        this.meetingRepository = meetingRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void setMeeting(Meeting meeting, String category) {
        meetingRepository.save(meeting);
        Meeting meeting1 = meetingRepository.findByName(meeting.getName());
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(meeting1);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setMeetings(meetings);
        categoryRepository.findByName(category).setMeetings(meetings);
    }
}
