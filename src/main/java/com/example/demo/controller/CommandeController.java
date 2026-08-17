package com.example.demo.controller;

import com.example.demo.enums.StatutCommandeEnum;
import com.example.demo.model.Commande;
import com.example.demo.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Commande> listerTous() {
        return commandeService.listerTous();
    }

    @GetMapping("/{id}")
    public Commande trouverParId(@PathVariable Long id) {
        return commandeService.trouverParId(id);
    }

    @PostMapping
    public Commande valider(@RequestBody Commande commande) {
        return commandeService.valider(commande);
    }

    @PutMapping("/{id}/annuler")
    public Commande annuler(@PathVariable Long id) {
        return commandeService.annuler(id);
    }

    @PutMapping("/{id}/statut")
    public Commande changerStatut(@PathVariable Long id, @RequestParam StatutCommandeEnum nouveauStatut) {
        return commandeService.changerStatut(id, nouveauStatut);
    }
}