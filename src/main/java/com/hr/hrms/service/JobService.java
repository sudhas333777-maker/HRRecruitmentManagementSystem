package com.hr.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.hrms.entity.Job;
import com.hr.hrms.repository.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    // Save
    public Job saveJob(Job job) {
        return repository.save(job);
    }

    // View All
    public List<Job> getAllJobs() {
        return repository.findAll();
    }

    // Get By ID
    public Job getJobById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Delete
    public void deleteJob(Long id) {
        repository.deleteById(id);
    }

    // Search
    public List<Job> searchJob(String keyword) {

        if(keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll();
        }

        return repository.findByJobTitleContainingIgnoreCase(keyword);

    }
    
    public long getJobCount(){

        return repository.count();

    }
 
}