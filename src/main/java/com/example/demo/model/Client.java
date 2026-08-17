package com.example.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Client extends Utilisateur {

    private String adresseDefaut;

    private String quartier;

    private int pointsFidelite;

    // Constructeurs
    public Client() {
    }

    // Getters et Setters
    public String getAdresseDefaut() {
        return adresseDefaut;
    }

    public void setAdresseDefaut(String adresseDefaut) {
        this.adresseDefaut = adresseDefaut;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public int getPointsFidelite() {
        return pointsFidelite;
    }

    public void setPointsFidelite(int pointsFidelite) {
        this.pointsFidelite = pointsFidelite;
    }
}