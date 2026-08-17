package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.StatutLivraisonEnum;
import com.example.demo.model.Commande;
import com.example.demo.model.Livraison;
import com.example.demo.repository.LivraisonRepository;

@Service
public class LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;

    @Autowired
    private CommandeService commandeService;

    public Livraison trouverParId(Long id) {
        return livraisonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livraison introuvable avec l'id : " + id));
    }

    public Livraison creerPourCommande(Long commandeId) {
        Commande commande = commandeService.trouverParId(commandeId);

        Livraison livraison = new Livraison();
        livraison.setCommande(commande);
        livraison.setStatut(StatutLivraisonEnum.EN_ATTENTE);

        return livraisonRepository.save(livraison);
    }

    public Livraison assignerLivreur(Long livraisonId, String nomLivreur) {
        Livraison livraison = trouverParId(livraisonId);
        livraison.setLivreur(nomLivreur);
        livraison.setStatut(StatutLivraisonEnum.ASSIGNEE);
        livraison.setDateEstimee(LocalDateTime.now().plusHours(2));
        return livraisonRepository.save(livraison);
    }

    public Livraison mettreAJourPosition(Long livraisonId, String position) {
        Livraison livraison = trouverParId(livraisonId);
        livraison.setPositionActuelle(position);
        livraison.setStatut(StatutLivraisonEnum.EN_COURS);
        return livraisonRepository.save(livraison);
    }

    public Livraison confirmerLivraison(Long livraisonId) {
        Livraison livraison = trouverParId(livraisonId);
        livraison.setStatut(StatutLivraisonEnum.LIVREE);
        livraison.setDateLivraisonReelle(LocalDateTime.now());
        return livraisonRepository.save(livraison);
    }
}