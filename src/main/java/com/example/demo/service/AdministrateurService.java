package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Administrateur;
import com.example.demo.model.Produit;
import com.example.demo.repository.AdministrateurRepository;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private ClientService clientService;

    public Administrateur creerCompte(Administrateur admin, String motDePasseEnClair) {
        administrateurRepository.findByTelephone(admin.getTelephone()).ifPresent(a -> {
            throw new RuntimeException("Un compte existe déjà avec ce numéro de téléphone.");
        });

        admin.setMotDePasseHash(passwordEncoder.encode(motDePasseEnClair));
        admin.setDateCreation(LocalDateTime.now());

        return administrateurRepository.save(admin);
    }

    public Administrateur seConnecter(String telephone, String motDePasseEnClair) {
        Administrateur admin = administrateurRepository.findByTelephone(telephone)
                .orElseThrow(() -> new RuntimeException("Numéro de téléphone ou mot de passe incorrect."));

        if (!passwordEncoder.matches(motDePasseEnClair, admin.getMotDePasseHash())) {
            throw new RuntimeException("Numéro de téléphone ou mot de passe incorrect.");
        }

        return admin;
    }

    public Produit modererProduit(Long produitId, boolean disponible) {
        Produit produit = produitService.trouverParId(produitId);
        produit.setDisponible(disponible);
        return produitService.publier(produit);
    }

    public Map<String, Object> consulterStatistiques() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("nombreProduits", produitService.listerTous().size());
        stats.put("nombreClients", clientService.listerTous().size());
        return stats;
    }
}