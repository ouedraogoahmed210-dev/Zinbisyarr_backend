package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.StatutCommandeEnum;
import com.example.demo.model.Commande;
import com.example.demo.model.LigneCommande;
import com.example.demo.model.Produit;
import com.example.demo.repository.CommandeRepository;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private ZoneLivraisonService zoneLivraisonService;

    public List<Commande> listerTous() {
        return commandeRepository.findAll();
    }

    public Commande trouverParId(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec l'id : " + id));
    }

    public Commande valider(Commande commande) {
        // Vérifie le stock de chaque produit avant de valider
        for (LigneCommande ligne : commande.getLignes()) {
            Produit produit = ligne.getProduit();
            if (produit.getStock() < ligne.getQuantite()) {
                throw new RuntimeException("Stock insuffisant pour le produit : " + produit.getNom());
            }
        }

        // Décrémente le stock
        for (LigneCommande ligne : commande.getLignes()) {
            Produit produit = ligne.getProduit();
            produitService.mettreAJourStock(produit.getId(), produit.getStock() - ligne.getQuantite());
        }

        commande.setStatut(StatutCommandeEnum.CONFIRMEE);
        commande.setDateCommande(LocalDateTime.now());
        commande.setFraisLivraison(zoneLivraisonService.calculerTarif(commande.getQuartier()));

        return commandeRepository.save(commande);
    }

    public Commande annuler(Long id) {
        Commande commande = trouverParId(id);
        commande.setStatut(StatutCommandeEnum.ANNULEE);
        return commandeRepository.save(commande);
    }

    public Commande changerStatut(Long id, StatutCommandeEnum nouveauStatut) {
        Commande commande = trouverParId(id);
        commande.setStatut(nouveauStatut);
        return commandeRepository.save(commande);
    }
}