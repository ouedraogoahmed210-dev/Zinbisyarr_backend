package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/inscription")
    public Client creerCompte(@RequestBody Client client, @RequestParam String motDePasse) {
        return clientService.creerCompte(client, motDePasse);
    }

    @PostMapping("/connexion")
    public Client seConnecter(@RequestParam String telephone, @RequestParam String motDePasse) {
        return clientService.seConnecter(telephone, motDePasse);
    }

    @GetMapping("/{id}")
    public Client trouverParId(@PathVariable Long id) {
        return clientService.trouverParId(id);
    }

    @PutMapping("/{id}")
    public Client modifierProfil(@PathVariable Long id, @RequestBody Client client) {
        return clientService.modifierProfil(id, client);
    }
}