package com.hr.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hr.hrms.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{

    List<Candidate> findByFullNameContainingIgnoreCase(String keyword);
    long countByStatus(String status);

}