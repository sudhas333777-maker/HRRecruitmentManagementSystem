package com.hr.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.hrms.entity.Candidate;
import com.hr.hrms.repository.CandidateRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository repository;

    public Candidate saveCandidate(Candidate candidate) {
        return repository.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return repository.findAll();
    }

    public Candidate getCandidateById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteCandidate(Long id) {
        repository.deleteById(id);
    }

    public List<Candidate> searchCandidate(String keyword) {

        if(keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll();
        }

        return repository.findByFullNameContainingIgnoreCase(keyword);
    }
    
    public long getCandidateCount(){

        return repository.count();

    }
    
    public long getSelectedCandidateCount() {
        return repository.countByStatus("Selected");
    }
    
    public Page<Candidate> getCandidatesByPage(int page){

        return repository.findAll(
                PageRequest.of(page, 10));

    }

}