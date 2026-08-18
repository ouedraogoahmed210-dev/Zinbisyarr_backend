package com.example.demo.controller;

import com.example.demo.enums.StatutCommandeEnum;
import com.example.demo.model.Commande;
import com.example.demo.security.AuthenticatedUser;
import com.example.demo.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Commande> listerPourUtilisateur(@AuthenticationPrincipal AuthenticatedUser user) {
        return commandeService.listerPourUtilisateur(user);
    }

    @GetMapping("/{id}")
    public Commande trouverParId(@PathVariable Long id, @AuthenticationPrincipal AuthenticatedUser user) {
        return commandeService.trouverParIdEtVerifierProprietaire(id, user);
    }

    @PostMapping
    public Commande valider(@RequestBody Commande commande) {
        return commandeService.valider(commande);
    }

    @PutMapping("/{id}/annuler")
    public Commande annuler(@PathVariable Long id, @AuthenticationPrincipal AuthenticatedUser user) {
        return commandeService.annuler(id, user);
    }

    @PutMapping("/{id}/statut")
    public Commande changerStatut(@PathVariable Long id, @RequestParam StatutCommandeEnum nouveauStatut) {
        return commandeService.changerStatut(id, nouveauStatut);
    }
}