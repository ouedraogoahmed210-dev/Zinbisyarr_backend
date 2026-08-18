package com.example.demo.service;

import com.example.demo.model.LignePanier;
import com.example.demo.model.Panier;
import com.example.demo.model.Produit;
import com.example.demo.repository.LignePanierRepository;
import com.example.demo.repository.PanierRepository;
import com.example.demo.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PanierService {

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private LignePanierRepository lignePanierRepository;

    @Autowired
    private ProduitService produitService;

    public Panier trouverParId(Long id) {
        return panierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Panier introuvable avec l'id : " + id));
    }

    public Panier trouverParIdEtVerifierProprietaire(Long id, AuthenticatedUser user) {
        Panier panier = trouverParId(id);

        if (!user.estAdmin() && !panier.getClient().getId().equals(user.getUserId())) {
            throw new RuntimeException("Accès refusé : ce panier ne vous appartient pas.");
        }

        return panier;
    }

    public Panier ajouterArticle(Long panierId, Long produitId, int quantite, AuthenticatedUser user) {
        Panier panier = trouverParIdEtVerifierProprietaire(panierId, user);
        Produit produit = produitService.trouverParId(produitId);

        for (LignePanier ligne : panier.getLignes()) {
            if (ligne.getProduit().getId().equals(produitId)) {
                ligne.setQuantite(ligne.getQuantite() + quantite);
                lignePanierRepository.save(ligne);
                return panier;
            }
        }

        LignePanier nouvelleLigne = new LignePanier();
        nouvelleLigne.setPanier(panier);
        nouvelleLigne.setProduit(produit);
        nouvelleLigne.setQuantite(quantite);
        nouvelleLigne.setPrixUnitaire(produit.getPrix());

        panier.getLignes().add(nouvelleLigne);
        return panierRepository.save(panier);
    }

    public Panier retirerArticle(Long panierId, Long ligneId, AuthenticatedUser user) {
        Panier panier = trouverParIdEtVerifierProprietaire(panierId, user);
        panier.getLignes().removeIf(ligne -> ligne.getId().equals(ligneId));
        return panierRepository.save(panier);
    }

    public BigDecimal calculerTotal(Long panierId, AuthenticatedUser user) {
        Panier panier = trouverParIdEtVerifierProprietaire(panierId, user);
        return panier.getLignes().stream()
                .map(ligne -> ligne.getPrixUnitaire().multiply(BigDecimal.valueOf(ligne.getQuantite())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Panier vider(Long panierId, AuthenticatedUser user) {
        Panier panier = trouverParIdEtVerifierProprietaire(panierId, user);
        panier.getLignes().clear();
        return panierRepository.save(panier);
    }
}