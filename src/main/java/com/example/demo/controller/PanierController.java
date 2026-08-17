package com.example.demo.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Panier;
import com.example.demo.service.PanierService;

@RestController
@RequestMapping("/api/paniers")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @GetMapping("/{id}")
    public Panier trouverParId(@PathVariable Long id) {
        return panierService.trouverParId(id);
    }

    @PostMapping("/{id}/articles")
    public Panier ajouterArticle(@PathVariable Long id,
                                  @RequestParam Long produitId,
                                  @RequestParam int quantite) {
        return panierService.ajouterArticle(id, produitId, quantite);
    }

    @DeleteMapping("/{id}/articles/{ligneId}")
    public Panier retirerArticle(@PathVariable Long id, @PathVariable Long ligneId) {
        return panierService.retirerArticle(id, ligneId);
    }

    @GetMapping("/{id}/total")
    public BigDecimal calculerTotal(@PathVariable Long id) {
        return panierService.calculerTotal(id);
    }

    @DeleteMapping("/{id}/vider")
    public Panier vider(@PathVariable Long id) {
        return panierService.vider(id);
    }
}