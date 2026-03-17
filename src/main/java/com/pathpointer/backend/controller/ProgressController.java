package com.pathpointer.backend.controller;

import com.pathpointer.backend.model.TopicStat;
import com.pathpointer.backend.service.ProgressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/progressTopics")
    public List<TopicStat> getProgressTopics(@RequestParam String email) {
        return progressService.getTopicProgressByEmail(email);
    }
}