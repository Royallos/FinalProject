package com.example.newsportal.controller;

import com.example.newsportal.model.Meeting;
import com.example.newsportal.model.User;
import com.example.newsportal.repository.MeetingRepository;
import com.example.newsportal.service.impl.MeetingServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "addMeeting";
    }

    @PostMapping("/addMeeting")
    public String saveMeeting(@ModelAttribute("newMeeting") Meeting meeting, @RequestParam("category") String category, Model model) {

        meetingService.setMeeting(meeting, category);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "showMeetings";
    }

//    Show meetings page

    @GetMapping("/showMeetings")
    public String showMeetings(Model model) {

        List<Meeting> meetings = meetingRepository.findAll();
        model.addAttribute("meetings", meetings);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "showMeetings";
    }

    @GetMapping("/meeting/{id}")
    public String meeting(@PathVariable("id") Long id, Model model) {

        Meeting meeting = meetingRepository.findById(id).get();
        model.addAttribute("meeting",meeting);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "meeting";
    }
}
