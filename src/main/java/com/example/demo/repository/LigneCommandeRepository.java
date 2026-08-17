package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.LigneCommande;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
}