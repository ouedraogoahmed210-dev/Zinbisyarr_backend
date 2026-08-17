package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Livraison;
import com.example.demo.service.LivraisonService;

@RestController
@RequestMapping("/api/livraisons")
public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;

    @PostMapping
    public Livraison creerPourCommande(@RequestParam Long commandeId) {
        return livraisonService.creerPourCommande(commandeId);
    }

    @PutMapping("/{id}/assigner")
    public Livraison assignerLivreur(@PathVariable Long id, @RequestParam String nomLivreur) {
        return livraisonService.assignerLivreur(id, nomLivreur);
    }

    @PutMapping("/{id}/position")
    public Livraison mettreAJourPosition(@PathVariable Long id, @RequestParam String position) {
        return livraisonService.mettreAJourPosition(id, position);
    }

    @PutMapping("/{id}/confirmer")
    public Livraison confirmerLivraison(@PathVariable Long id) {
        return livraisonService.confirmerLivraison(id);
    }
}