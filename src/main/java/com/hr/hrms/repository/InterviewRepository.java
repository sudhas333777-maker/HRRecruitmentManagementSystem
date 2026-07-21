package com.hr.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hr.hrms.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findByCandidateNameContainingIgnoreCase(String keyword);
    
    long count();

    long countByStatus(String status);

}