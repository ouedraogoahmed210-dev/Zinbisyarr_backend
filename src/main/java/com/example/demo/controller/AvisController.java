package com.example.demo.controller;

import com.example.demo.model.Avis;
import com.example.demo.security.AuthenticatedUser;
import com.example.demo.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
public class AvisController {

    @Autowired
    private AvisService avisService;

    @GetMapping("/produit/{produitId}")
    public List<Avis> listerParProduit(@PathVariable Long produitId) {
        return avisService.listerParProduit(produitId);
    }

    @PostMapping
    public Avis publierAvis(@RequestParam Long produitId,
                             @RequestParam int note,
                             @RequestParam String commentaire,
                             @AuthenticationPrincipal AuthenticatedUser user) {
        return avisService.publierAvis(produitId, note, commentaire, user);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id, @AuthenticationPrincipal AuthenticatedUser user) {
        avisService.supprimer(id, user);
    }
}