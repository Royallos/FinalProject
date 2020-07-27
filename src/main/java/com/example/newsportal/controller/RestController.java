//package com.example.newsportal.controller;
//
//import com.example.newsportal.model.Speaker;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@org.springframework.web.bind.annotation.RestController
//@RequestMapping(value ="/speakers")
//public class RestController {
//
//    private final SpeakerRepository speakerRepository;
//
//    @Autowired
//    public RestController(SpeakerRepository speakerRepository) {
//        this.speakerRepository = speakerRepository;
//    }
//
//
//
//
//
//
//    @GetMapping("/{id}")
//    public List<Speaker> getById(@PathVariable (name = "id") Long id) {
//        List<Speaker> speakerList = new ArrayList<>();
//        speakerList.add(speakerRepository.findById(id).get());
//        return speakerList;
//    }
//    @GetMapping("/")
//    public List<Speaker> getAll() {
//        List<Speaker> speakerList = speakerRepository.findAll();
//        return speakerList;
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable (name = "id") Long id) {
//        speakerRepository.deleteById(id);
//    }
//
//    @PostMapping("/")
//    public void add (@RequestBody Speaker speaker) {
//        speakerRepository.save(speaker);
//    }
//
//    @PutMapping ("/{id}")
//    public void update(@PathVariable Long id, @RequestBody Speaker speaker) {
//        speaker.setId(id);
//        speakerRepository.save(speaker);
//    }
//}
