package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ZoneLivraison;

public interface ZoneLivraisonRepository extends JpaRepository<ZoneLivraison, Long> {
    Optional<ZoneLivraison> findByNomQuartierIgnoreCase(String nomQuartier);
}