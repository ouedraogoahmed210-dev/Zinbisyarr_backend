package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Produit;
import com.example.demo.service.ProduitService;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public List<Produit> listerTous() {
        return produitService.listerTous();
    }

    @GetMapping("/{id}")
    public Produit trouverParId(@PathVariable Long id) {
        return produitService.trouverParId(id);
    }

    @PostMapping
    public Produit publier(@RequestBody Produit produit) {
        return produitService.publier(produit);
    }

    @PutMapping("/{id}/stock")
    public Produit mettreAJourStock(@PathVariable Long id, @RequestParam int quantite) {
        return produitService.mettreAJourStock(id, quantite);
    }

    @DeleteMapping("/{id}")
    public Produit archiver(@PathVariable Long id) {
        return produitService.archiver(id);
    }
}