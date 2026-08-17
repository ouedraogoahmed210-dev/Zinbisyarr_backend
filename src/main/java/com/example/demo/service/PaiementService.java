package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.StatutPaiementEnum;
import com.example.demo.enums.TypePaiementEnum;
import com.example.demo.model.Commande;
import com.example.demo.model.Paiement;
import com.example.demo.repository.PaiementRepository;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private CommandeService commandeService;

    public Paiement trouverParId(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable avec l'id : " + id));
    }

    public Paiement initierPaiement(Long commandeId, TypePaiementEnum type, String operateurMobileMoney) {
        Commande commande = commandeService.trouverParId(commandeId);

        Paiement paiement = new Paiement();
        paiement.setCommande(commande);
        paiement.setMontant(commande.getMontantTotal());
        paiement.setType(type);
        paiement.setStatut(StatutPaiementEnum.EN_ATTENTE);
        paiement.setOperateurMobileMoney(operateurMobileMoney);
        paiement.setReferenceTransaction(UUID.randomUUID().toString());

        return paiementRepository.save(paiement);
    }

    public Paiement confirmerPaiement(Long paiementId) {
        Paiement paiement = trouverParId(paiementId);
        paiement.setStatut(StatutPaiementEnum.REUSSI);
        paiement.setDatePaiement(LocalDateTime.now());
        return paiementRepository.save(paiement);
    }

    public Paiement rembourser(Long paiementId) {
        Paiement paiement = trouverParId(paiementId);

        if (paiement.getStatut() != StatutPaiementEnum.REUSSI) {
            throw new RuntimeException("Seul un paiement réussi peut être remboursé.");
        }

        paiement.setStatut(StatutPaiementEnum.REMBOURSE);
        return paiementRepository.save(paiement);
    }
}