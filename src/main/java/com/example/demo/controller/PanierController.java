package com.example.demo.controller;

import com.example.demo.model.Panier;
import com.example.demo.security.AuthenticatedUser;
import com.example.demo.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/paniers")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @GetMapping("/{id}")
    public Panier trouverParId(@PathVariable Long id, @AuthenticationPrincipal AuthenticatedUser user) {
        return panierService.trouverParIdEtVerifierProprietaire(id, user);
    }

    @PostMapping("/{id}/articles")
    public Panier ajouterArticle(@PathVariable Long id,
                                  @RequestParam Long produitId,
                                  @RequestParam int quantite,
                                  @AuthenticationPrincipal AuthenticatedUser user) {
        return panierService.ajouterArticle(id, produitId, quantite, user);
    }

    @DeleteMapping("/{id}/articles/{ligneId}")
    public Panier retirerArticle(@PathVariable Long id, @PathVariable Long ligneId,
                                  @AuthenticationPrincipal AuthenticatedUser user) {
        return panierService.retirerArticle(id, ligneId, user);
    }

    @GetMapping("/{id}/total")
    public BigDecimal calculerTotal(@PathVariable Long id, @AuthenticationPrincipal AuthenticatedUser user) {
        return panierService.calculerTotal(id, user);
    }

    @DeleteMapping("/{id}/vider")
    public Panier vider(@PathVariable Long id, @AuthenticationPrincipal AuthenticatedUser user) {
        return panierService.vider(id, user);
    }
}