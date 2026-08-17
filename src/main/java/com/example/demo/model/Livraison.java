package com.example.demo.model;

import java.time.LocalDateTime;

import com.example.demo.enums.StatutLivraisonEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Livraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatutLivraisonEnum statut;

    private String livreur;

    private LocalDateTime dateEstimee;

    private LocalDateTime dateLivraisonReelle;

    private String positionActuelle;

    @OneToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    // Constructeurs
    public Livraison() {
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatutLivraisonEnum getStatut() {
        return statut;
    }

    public void setStatut(StatutLivraisonEnum statut) {
        this.statut = statut;
    }

    public String getLivreur() {
        return livreur;
    }

    public void setLivreur(String livreur) {
        this.livreur = livreur;
    }

    public LocalDateTime getDateEstimee() {
        return dateEstimee;
    }

    public void setDateEstimee(LocalDateTime dateEstimee) {
        this.dateEstimee = dateEstimee;
    }

    public LocalDateTime getDateLivraisonReelle() {
        return dateLivraisonReelle;
    }

    public void setDateLivraisonReelle(LocalDateTime dateLivraisonReelle) {
        this.dateLivraisonReelle = dateLivraisonReelle;
    }

    public String getPositionActuelle() {
        return positionActuelle;
    }

    public void setPositionActuelle(String positionActuelle) {
        this.positionActuelle = positionActuelle;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}