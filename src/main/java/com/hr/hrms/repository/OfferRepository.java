package com.hr.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hr.hrms.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long>{

    List<Offer> findByCandidateNameContainingIgnoreCase(String keyword);
    
    long count();
    long countByStatus(String status);

}