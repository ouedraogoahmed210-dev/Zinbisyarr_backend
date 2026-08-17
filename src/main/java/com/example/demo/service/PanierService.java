package com.example.demo.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LignePanier;
import com.example.demo.model.Panier;
import com.example.demo.model.Produit;
import com.example.demo.repository.LignePanierRepository;
import com.example.demo.repository.PanierRepository;

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

    public Panier ajouterArticle(Long panierId, Long produitId, int quantite) {
        Panier panier = trouverParId(panierId);
        Produit produit = produitService.trouverParId(produitId);

        // Vérifie si le produit est déjà dans le panier, on augmente juste la quantité
        for (LignePanier ligne : panier.getLignes()) {
            if (ligne.getProduit().getId().equals(produitId)) {
                ligne.setQuantite(ligne.getQuantite() + quantite);
                lignePanierRepository.save(ligne);
                return panier;
            }
        }

        // Sinon on crée une nouvelle ligne
        LignePanier nouvelleLigne = new LignePanier();
        nouvelleLigne.setPanier(panier);
        nouvelleLigne.setProduit(produit);
        nouvelleLigne.setQuantite(quantite);
        nouvelleLigne.setPrixUnitaire(produit.getPrix());

        panier.getLignes().add(nouvelleLigne);
        return panierRepository.save(panier);
    }

    public Panier retirerArticle(Long panierId, Long ligneId) {
        Panier panier = trouverParId(panierId);
        panier.getLignes().removeIf(ligne -> ligne.getId().equals(ligneId));
        return panierRepository.save(panier);
    }

    public BigDecimal calculerTotal(Long panierId) {
        Panier panier = trouverParId(panierId);
        return panier.getLignes().stream()
                .map(ligne -> ligne.getPrixUnitaire().multiply(BigDecimal.valueOf(ligne.getQuantite())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Panier vider(Long panierId) {
        Panier panier = trouverParId(panierId);
        panier.getLignes().clear();
        return panierRepository.save(panier);
    }
}