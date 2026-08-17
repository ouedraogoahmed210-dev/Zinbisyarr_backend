package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Administrateur;
import com.example.demo.model.Produit;
import com.example.demo.service.AdministrateurService;

@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    @PostMapping("/inscription")
    public Administrateur creerCompte(@RequestBody Administrateur admin, @RequestParam String motDePasse) {
        return administrateurService.creerCompte(admin, motDePasse);
    }

    @PostMapping("/connexion")
    public Administrateur seConnecter(@RequestParam String telephone, @RequestParam String motDePasse) {
        return administrateurService.seConnecter(telephone, motDePasse);
    }

    @PutMapping("/produits/{produitId}/moderer")
    public Produit modererProduit(@PathVariable Long produitId, @RequestParam boolean disponible) {
        return administrateurService.modererProduit(produitId, disponible);
    }

    @GetMapping("/statistiques")
    public Map<String, Object> consulterStatistiques() {
        return administrateurService.consulterStatistiques();
    }
}