package com.example.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Administrateur extends Utilisateur {

    private String niveauAcces;

    // Constructeurs
    public Administrateur() {
    }

    // Getters et Setters
    public String getNiveauAcces() {
        return niveauAcces;
    }

    public void setNiveauAcces(String niveauAcces) {
        this.niveauAcces = niveauAcces;
    }
}