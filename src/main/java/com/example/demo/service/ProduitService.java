package com.example.demo.service;

import com.example.demo.model.Produit;
import com.example.demo.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> listerTous() {
        return produitRepository.findAll();
    }

    public Produit trouverParId(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable avec l'id : " + id));
    }

    public Produit publier(Produit produit) {
        produit.setDisponible(true);
        produit.setDateMiseAJour(LocalDateTime.now());
        return produitRepository.save(produit);
    }

    public Produit mettreAJourStock(Long id, int nouveauStock) {
        Produit produit = trouverParId(id);
        produit.setStock(nouveauStock);
        produit.setDateMiseAJour(LocalDateTime.now());
        return produitRepository.save(produit);
    }

    public Produit archiver(Long id) {
        Produit produit = trouverParId(id);
        produit.setDisponible(false);
        produit.setDateMiseAJour(LocalDateTime.now());
        return produitRepository.save(produit);
    }
}