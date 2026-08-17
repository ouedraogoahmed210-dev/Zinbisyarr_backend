package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Administrateur;
import com.example.demo.model.CampagneMarketing;
import com.example.demo.service.CampagneMarketingService;

@RestController
@RequestMapping("/api/campagnes")
public class CampagneMarketingController {

    @Autowired
    private CampagneMarketingService campagneMarketingService;

    @GetMapping
    public List<CampagneMarketing> listerToutes() {
        return campagneMarketingService.listerToutes();
    }

    @PostMapping
    public CampagneMarketing creerCampagne(@RequestParam Long administrateurId,
                                            @RequestParam String titre,
                                            @RequestParam String message,
                                            @RequestParam String segmentCible,
                                            @RequestParam List<Long> clientIds) {
        // Note : administrateurId est reçu mais l'objet Administrateur complet
        // doit être récupéré avant d'appeler le service (voir remarque ci-dessous)
        Administrateur admin = new Administrateur();
        admin.setId(administrateurId);
        return campagneMarketingService.creerCampagne(admin, titre, message, segmentCible, clientIds);
    }

    @PutMapping("/{id}/envoyer")
    public CampagneMarketing envoyerMessageGroupe(@PathVariable Long id) {
        return campagneMarketingService.envoyerMessageGroupe(id);
    }
}