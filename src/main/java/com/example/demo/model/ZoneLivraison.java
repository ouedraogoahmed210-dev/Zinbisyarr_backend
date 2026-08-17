package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ZoneLivraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomQuartier;

    private BigDecimal tarif;

    // Constructeurs, getters, setters
    public ZoneLivraison() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomQuartier() { return nomQuartier; }
    public void setNomQuartier(String nomQuartier) { this.nomQuartier = nomQuartier; }
    public BigDecimal getTarif() { return tarif; }
    public void setTarif(BigDecimal tarif) { this.tarif = tarif; }
}