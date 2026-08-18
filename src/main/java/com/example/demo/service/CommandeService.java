package com.example.demo.service;

import com.example.demo.enums.StatutCommandeEnum;
import com.example.demo.model.Commande;
import com.example.demo.model.LigneCommande;
import com.example.demo.model.Produit;
import com.example.demo.repository.CommandeRepository;
import com.example.demo.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<Commande> listerPourUtilisateur(AuthenticatedUser user) {
        if (user.estAdmin()) {
            return commandeRepository.findAll();
        }
        return commandeRepository.findByClientId(user.getUserId());
    }

    public Commande trouverParId(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec l'id : " + id));
    }

    public Commande trouverParIdEtVerifierProprietaire(Long id, AuthenticatedUser user) {
        Commande commande = trouverParId(id);

        if (!user.estAdmin() && !commande.getClient().getId().equals(user.getUserId())) {
            throw new RuntimeException("Accès refusé : cette commande ne vous appartient pas.");
        }

        return commande;
    }

    public Commande valider(Commande commande) {
        for (LigneCommande ligne : commande.getLignes()) {
            Produit produit = ligne.getProduit();
            if (produit.getStock() < ligne.getQuantite()) {
                throw new RuntimeException("Stock insuffisant pour le produit : " + produit.getNom());
            }
        }

        for (LigneCommande ligne : commande.getLignes()) {
            Produit produit = ligne.getProduit();
            produitService.mettreAJourStock(produit.getId(), produit.getStock() - ligne.getQuantite());
        }

        commande.setStatut(StatutCommandeEnum.CONFIRMEE);
        commande.setDateCommande(LocalDateTime.now());
        commande.setFraisLivraison(zoneLivraisonService.calculerTarif(commande.getQuartier()));

        return commandeRepository.save(commande);
    }

    public Commande annuler(Long id, AuthenticatedUser user) {
        Commande commande = trouverParIdEtVerifierProprietaire(id, user);
        commande.setStatut(StatutCommandeEnum.ANNULEE);
        return commandeRepository.save(commande);
    }

    public Commande changerStatut(Long id, StatutCommandeEnum nouveauStatut) {
        Commande commande = trouverParId(id);
        commande.setStatut(nouveauStatut);
        return commandeRepository.save(commande);
    }
}