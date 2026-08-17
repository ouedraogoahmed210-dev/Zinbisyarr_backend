package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class CampagneMarketing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Column(length = 1000)
    private String message;

    private LocalDateTime dateEnvoi;

    private String segmentCible;

    @ManyToOne
    @JoinColumn(name = "administrateur_id")
    private Administrateur administrateur;

    @ManyToMany
    @JoinTable(
        name = "campagne_client",
        joinColumns = @JoinColumn(name = "campagne_id"),
        inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> clientsCibles = new ArrayList<>();

    // Constructeurs
    public CampagneMarketing() {
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getSegmentCible() {
        return segmentCible;
    }

    public void setSegmentCible(String segmentCible) {
        this.segmentCible = segmentCible;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public List<Client> getClientsCibles() {
        return clientsCibles;
    }

    public void setClientsCibles(List<Client> clientsCibles) {
        this.clientsCibles = clientsCibles;
    }
}