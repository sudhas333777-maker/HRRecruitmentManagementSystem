package com.hr.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hr.hrms.service.CandidateService;
import com.hr.hrms.service.InterviewService;
import com.hr.hrms.service.JobService;
import com.hr.hrms.service.OfferService;
import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private InterviewService interviewService;
    
    @Autowired
    private OfferService offerService;

    //http://localhost:8080/dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session){

        model.addAttribute("jobCount",
                jobService.getJobCount());

        model.addAttribute("candidateCount",
                candidateService.getCandidateCount());

        model.addAttribute("interviewCount",
                interviewService.getInterviewCount());

        model.addAttribute("selectedCount",
                candidateService.getSelectedCandidateCount());


        // Get logged-in user
        Object user = session.getAttribute("user");

        if(user != null) {
            model.addAttribute("loggedUser", user);
        }


        return "dashboard";
    }

}