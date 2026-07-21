package com.hr.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hr.hrms.entity.Interview;
import com.hr.hrms.service.CandidateService;
import com.hr.hrms.service.EmailService;
import com.hr.hrms.service.InterviewService;

@Controller
@RequestMapping("/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private CandidateService candidateService;
    
    @Autowired
    private EmailService emailService;

    //http://localhost:8080/interviews/add
    @GetMapping("/add")
    public String addInterview(Model model) {

        model.addAttribute("interview", new Interview());
        model.addAttribute("candidates", candidateService.getAllCandidates());

        return "interview";
    }

    @PostMapping("/save")
    public String saveInterview(@ModelAttribute Interview interview) {

        interviewService.saveInterview(interview);

        String subject = "Interview Scheduled";

        String body =
                "Dear Candidate,\n\n" +
                "Your interview has been scheduled.\n\n" +
                "Job : " + interview.getJobTitle() +
                "\nDate : " + interview.getInterviewDate() +
                "\nTime : " + interview.getInterviewTime() +
                "\nMode : " + interview.getInterviewMode() +
                "\nMeeting Link / Location : " + interview.getInterviewLocation() +
                "\n\nRegards,\nHR Team";

        try {
            emailService.sendEmail(
                    interview.getCandidateEmail(),
                    subject,
                    body);
        } catch (Exception e) {
            System.out.println("Email sending failed: " + e.getMessage());
        }

        return "redirect:/interviews";
    }

    @GetMapping
    public String interviewList(Model model) {

        model.addAttribute("interviews",
                interviewService.getAllInterviews());

        return "interview-list";
    }

    @GetMapping("/edit/{id}")
    public String editInterview(@PathVariable Long id,
                                Model model) {

        model.addAttribute("interview",
                interviewService.getInterviewById(id));

        model.addAttribute("candidates",
                candidateService.getAllCandidates());

        return "interview";
    }

    @GetMapping("/delete/{id}")
    public String deleteInterview(@PathVariable Long id) {

        interviewService.deleteInterview(id);

        return "redirect:/interviews";
    }

    @GetMapping("/search")
    public String searchInterview(@RequestParam String keyword,
                                  Model model) {

        model.addAttribute("interviews",
                interviewService.searchInterview(keyword));

        return "interview-list";
    }
}