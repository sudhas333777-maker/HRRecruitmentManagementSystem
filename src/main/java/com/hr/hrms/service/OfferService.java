package com.hr.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.hrms.entity.Offer;
import com.hr.hrms.repository.OfferRepository;

@Service
public class OfferService {

    @Autowired
    private OfferRepository repository;

    public Offer saveOffer(Offer offer) {
        return repository.save(offer);
    }

    public List<Offer> getAllOffers() {
        return repository.findAll();
    }

    public Offer getOfferById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteOffer(Long id) {
        repository.deleteById(id);
    }

    public List<Offer> searchOffer(String keyword) {

        if(keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll();
        }

        return repository.findByCandidateNameContainingIgnoreCase(keyword);
    }

    public long getOfferCount() {
        return repository.count();
    }
}