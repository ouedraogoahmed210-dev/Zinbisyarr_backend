package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CampagneMarketing;

public interface CampagneMarketingRepository extends JpaRepository<CampagneMarketing, Long> {
}