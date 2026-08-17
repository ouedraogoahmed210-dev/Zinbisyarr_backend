package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Panier;

public interface PanierRepository extends JpaRepository<Panier, Long> {
}