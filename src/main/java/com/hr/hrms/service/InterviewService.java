package com.hr.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.hrms.entity.Interview;
import com.hr.hrms.repository.InterviewRepository;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository repository;

    public Interview saveInterview(Interview interview) {
        return repository.save(interview);
    }

    public List<Interview> getAllInterviews() {
        return repository.findAll();
    }

    public Interview getInterviewById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteInterview(Long id) {
        repository.deleteById(id);
    }

    public List<Interview> searchInterview(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll();
        }

        return repository.findByCandidateNameContainingIgnoreCase(keyword);
    }

    public long getInterviewCount() {
        return repository.count();
    }
}