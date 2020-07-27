package com.example.newsportal.controller;

import com.example.newsportal.model.Meeting;
import com.example.newsportal.model.User;
import com.example.newsportal.repository.MeetingRepository;
import com.example.newsportal.service.CategoryService;
import com.example.newsportal.service.MeetingService;
import com.example.newsportal.service.impl.MeetingServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MeetingController {

    private final MeetingServiceImpl meetingService;
    private final MeetingRepository meetingRepository;

    public MeetingController(MeetingServiceImpl meetingService, MeetingRepository meetingRepository) {
        this.meetingService = meetingService;
        this.meetingRepository = meetingRepository;
    }


    //    AddMeeting page settings

    @GetMapping("/addMeeting")
    public String addMeeting(Model model) {

        model.addAttribute("newMeeting", new Meeting());
        return "addMeeting";
    }

    @PostMapping("/addMeeting")
    public String saveMeeting(@ModelAttribute("newMeeting") Meeting meeting, @RequestParam("category") String category) {

        meetingService.setMeeting(meeting, category);
        return "index";
    }

//    Show meetings page

    @GetMapping("/showMeetings")
    public String showMeetings(Model model) {

        User user = new User();
        user.setFirstName("Vasya");
        List<Meeting> meetings = meetingRepository.findAll();
        model.addAttribute("meetings", meetings);
        model.addAttribute("user", user);
        return "showMeetings";
    }

}
