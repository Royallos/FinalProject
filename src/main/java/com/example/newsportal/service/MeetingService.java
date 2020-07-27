package com.example.newsportal.service;

import com.example.newsportal.model.Meeting;
import org.springframework.stereotype.Service;

public interface MeetingService {

    void setMeeting(Meeting meeting, String category);
}
