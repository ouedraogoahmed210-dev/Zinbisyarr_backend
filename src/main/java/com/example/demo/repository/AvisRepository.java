package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Avis;

public interface AvisRepository extends JpaRepository<Avis, Long> {
    List<Avis> findByProduitId(Long produitId);
}