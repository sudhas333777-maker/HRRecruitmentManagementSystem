package com.hr.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hr.hrms.entity.Candidate;
import com.hr.hrms.service.CandidateService;
import com.hr.hrms.service.JobService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import com.hr.hrms.util.CandidatePDFExporter;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private JobService jobService;

    //http://localhost:8080/candidates/add
    @GetMapping("/add")
    public String addCandidatePage(Model model) {

        model.addAttribute("candidate", new Candidate());
        model.addAttribute("jobs", jobService.getAllJobs());

        return "add-candidate";
    }

    @PostMapping("/save")
    public String saveCandidate(@ModelAttribute Candidate candidate) {

        candidateService.saveCandidate(candidate);

        return "redirect:/candidates";
    }

    @GetMapping
    public String candidateList(

            @RequestParam(defaultValue = "0") int page,

            Model model){

        Page<Candidate> candidatePage =
                candidateService.getCandidatesByPage(page);

        model.addAttribute("candidatePage", candidatePage);

        model.addAttribute("candidates",
                candidatePage.getContent());

        model.addAttribute("currentPage", page);

        model.addAttribute("totalPages",
                candidatePage.getTotalPages());

        return "candidate-list";

    }

    @GetMapping("/edit/{id}")
    public String editCandidate(@PathVariable Long id,
                                Model model) {

        model.addAttribute("candidate",
                candidateService.getCandidateById(id));

        model.addAttribute("jobs",
                jobService.getAllJobs());

        return "add-candidate";
    }

    @GetMapping("/delete/{id}")
    public String deleteCandidate(@PathVariable Long id) {

        candidateService.deleteCandidate(id);

        return "redirect:/candidates";
    }

    @GetMapping("/search")
    public String searchCandidate(@RequestParam String keyword,
                                  Model model) {

        model.addAttribute("candidates",
                candidateService.searchCandidate(keyword));

        // Required for candidate-list.html
        model.addAttribute("currentPage", 0);
        model.addAttribute("totalPages", 1);

        return "candidate-list";
    }
    
    //http://localhost:8080/candidates
    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response)
            throws DocumentException, IOException {

        response.setContentType("application/pdf");

        DateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

        String currentDateTime =
                dateFormat.format(new Date());

        String headerKey =
                "Content-Disposition";

        String headerValue =
                "attachment; filename=candidates_"
                        + currentDateTime
                        + ".pdf";

        response.setHeader(headerKey,
                headerValue);

        CandidatePDFExporter exporter =
                new CandidatePDFExporter(
                        candidateService.getAllCandidates());

        exporter.export(response);

    }

}