package com.hr.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hr.hrms.entity.Offer;
import com.hr.hrms.service.CandidateService;
import com.hr.hrms.service.EmailService;
import com.hr.hrms.service.OfferService;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private CandidateService candidateService;
    
    @Autowired
    private EmailService emailService;

    //http://localhost:8080/offers/add
    @GetMapping("/add")
    public String addOffer(Model model){

        model.addAttribute("offer", new Offer());
        model.addAttribute("candidates", candidateService.getAllCandidates());

        return "offer";
    }

    @PostMapping("/save")
    public String saveOffer(@ModelAttribute Offer offer){

        offerService.saveOffer(offer);

        String subject = "Offer Letter";

        String body =
                "Congratulations!\n\n" +
                "You have been selected for the position of "
                + offer.getJobTitle() +
                ".\n\nSalary : "
                + offer.getSalaryPackage() +
                "\nJoining Date : "
                + offer.getJoiningDate() +
                "\n\nRegards,\nHR Team";

        emailService.sendEmail(
                offer.getCandidateEmail(),
                subject,
                body);

        return "redirect:/offers";
    }

    @GetMapping
    public String offerList(Model model){

        model.addAttribute("offers", offerService.getAllOffers());

        return "offer-list";
    }

    @GetMapping("/edit/{id}")
    public String editOffer(@PathVariable Long id,
                            Model model){

        model.addAttribute("offer",
                offerService.getOfferById(id));

        model.addAttribute("candidates",
                candidateService.getAllCandidates());

        return "offer";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffer(@PathVariable Long id){

        offerService.deleteOffer(id);

        return "redirect:/offers";
    }

    @GetMapping("/search")
    public String searchOffer(@RequestParam String keyword,
                              Model model){

        model.addAttribute("offers",
                offerService.searchOffer(keyword));

        return "offer-list";
    }
}