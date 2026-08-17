package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.enums.StatutPaiementEnum;
import com.example.demo.enums.TypePaiementEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal montant;

    @Enumerated(EnumType.STRING)
    private TypePaiementEnum type;

    @Enumerated(EnumType.STRING)
    private StatutPaiementEnum statut;

    private String referenceTransaction;

    private LocalDateTime datePaiement;

    private String operateurMobileMoney;

    @OneToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    // Constructeurs
    public Paiement() {
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public TypePaiementEnum getType() {
        return type;
    }

    public void setType(TypePaiementEnum type) {
        this.type = type;
    }

    public StatutPaiementEnum getStatut() {
        return statut;
    }

    public void setStatut(StatutPaiementEnum statut) {
        this.statut = statut;
    }

    public String getReferenceTransaction() {
        return referenceTransaction;
    }

    public void setReferenceTransaction(String referenceTransaction) {
        this.referenceTransaction = referenceTransaction;
    }

    public LocalDateTime getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDateTime datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getOperateurMobileMoney() {
        return operateurMobileMoney;
    }

    public void setOperateurMobileMoney(String operateurMobileMoney) {
        this.operateurMobileMoney = operateurMobileMoney;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}