package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Avis;
import com.example.demo.service.AvisService;

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
    public Avis publierAvis(@RequestParam Long clientId,
                             @RequestParam Long produitId,
                             @RequestParam int note,
                             @RequestParam String commentaire) {
        return avisService.publierAvis(clientId, produitId, note, commentaire);
    }
}