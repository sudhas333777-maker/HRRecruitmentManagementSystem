package com.hr.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hr.hrms.entity.Candidate;
import com.hr.hrms.entity.Job;
import com.hr.hrms.service.CandidateService;
import com.hr.hrms.service.JobService;

@Controller
public class PublicJobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/public/jobs")
    public String viewJobs(Model model){

        model.addAttribute("jobs",
                jobService.getAllJobs());

        return "public-job-list";
    }
    
    @GetMapping("/apply/{id}")
    public String applyJob(@PathVariable Long id, Model model) {

        Candidate candidate = new Candidate();

        Job job = jobService.getJobById(id);

        candidate.setAppliedJob(job.getJobTitle());

        model.addAttribute("candidate", candidate);

        return "public-apply";
    }
    
    @PostMapping("/apply/save")
    public String saveApplication(@ModelAttribute Candidate candidate) {

        candidate.setStatus("Applied");

        candidateService.saveCandidate(candidate);

        return "redirect:/apply-success";
    }
    
    @GetMapping("/apply-success")
    public String success() {

        return "apply-success";
    }

}