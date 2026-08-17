package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Livraison;

public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
}