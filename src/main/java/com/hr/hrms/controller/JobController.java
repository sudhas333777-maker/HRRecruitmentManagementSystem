package com.hr.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hr.hrms.entity.Job;
import com.hr.hrms.service.JobService;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService service;

    //http://localhost:8080/jobs/add
    // Add Job Page
    @GetMapping("/add")
    public String addJobPage(Model model) {

        model.addAttribute("job", new Job());

        return "add-job";
    }

    // Save Job
    @PostMapping("/save")
    public String saveJob(@ModelAttribute Job job) {

        service.saveJob(job);

        return "redirect:/jobs";
    }

    // View Jobs
    @GetMapping
    public String viewJobs(Model model) {

        model.addAttribute("jobs", service.getAllJobs());

        return "job-list";
    }

    // Edit Job
    @GetMapping("/edit/{id}")
    public String editJob(@PathVariable Long id,
                          Model model) {

        model.addAttribute("job", service.getJobById(id));

        return "add-job";
    }

    // Delete Job
    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {

        service.deleteJob(id);

        return "redirect:/jobs";
    }

    // Search
    @GetMapping("/search")
    public String searchJob(@RequestParam String keyword,
                            Model model) {

        model.addAttribute("jobs",
                service.searchJob(keyword));

        return "job-list";
    }

}