package com.example.demo.service;

import com.example.demo.model.Avis;
import com.example.demo.model.Client;
import com.example.demo.model.Produit;
import com.example.demo.repository.AvisRepository;
import com.example.demo.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvisService {

    @Autowired
    private AvisRepository avisRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProduitService produitService;

    public List<Avis> listerParProduit(Long produitId) {
        return avisRepository.findByProduitId(produitId);
    }

    public Avis trouverParId(Long id) {
        return avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis introuvable avec l'id : " + id));
    }

    public Avis trouverParIdEtVerifierProprietaire(Long id, AuthenticatedUser user) {
        Avis avis = trouverParId(id);

        if (!user.estAdmin() && !avis.getClient().getId().equals(user.getUserId())) {
            throw new RuntimeException("Accès refusé : cet avis ne vous appartient pas.");
        }

        return avis;
    }

    public Avis publierAvis(Long produitId, int note, String commentaire, AuthenticatedUser user) {
        if (note < 1 || note > 5) {
            throw new RuntimeException("La note doit être comprise entre 1 et 5.");
        }

        Client client = clientService.trouverParId(user.getUserId());
        Produit produit = produitService.trouverParId(produitId);

        Avis avis = new Avis();
        avis.setClient(client);
        avis.setProduit(produit);
        avis.setNote(note);
        avis.setCommentaire(commentaire);
        avis.setDate(LocalDateTime.now());

        return avisRepository.save(avis);
    }

    public void supprimer(Long id, AuthenticatedUser user) {
        Avis avis = trouverParIdEtVerifierProprietaire(id, user);
        avisRepository.delete(avis);
    }
}