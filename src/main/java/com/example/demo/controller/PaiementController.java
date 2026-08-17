package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.enums.TypePaiementEnum;
import com.example.demo.model.Paiement;
import com.example.demo.service.PaiementService;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    @PostMapping("/initier")
    public Paiement initierPaiement(@RequestParam Long commandeId,
                                     @RequestParam TypePaiementEnum type,
                                     @RequestParam(required = false) String operateurMobileMoney) {
        return paiementService.initierPaiement(commandeId, type, operateurMobileMoney);
    }

    @PutMapping("/{id}/confirmer")
    public Paiement confirmerPaiement(@PathVariable Long id) {
        return paiementService.confirmerPaiement(id);
    }

    @PutMapping("/{id}/rembourser")
    public Paiement rembourser(@PathVariable Long id) {
        return paiementService.rembourser(id);
    }
}