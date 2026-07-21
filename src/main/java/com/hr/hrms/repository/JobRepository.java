package com.hr.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hr.hrms.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	
	List<Job> findByJobTitleContainingIgnoreCase(String keyword);

}
