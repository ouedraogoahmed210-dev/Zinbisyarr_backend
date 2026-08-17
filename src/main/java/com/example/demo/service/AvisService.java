package com.example.demo.service;

import com.example.demo.model.Avis;
import com.example.demo.model.Client;
import com.example.demo.model.Produit;
import com.example.demo.repository.AvisRepository;
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

    public Avis publierAvis(Long clientId, Long produitId, int note, String commentaire) {
        if (note < 1 || note > 5) {
            throw new RuntimeException("La note doit être comprise entre 1 et 5.");
        }

        Client client = clientService.trouverParId(clientId);
        Produit produit = produitService.trouverParId(produitId);

        Avis avis = new Avis();
        avis.setClient(client);
        avis.setProduit(produit);
        avis.setNote(note);
        avis.setCommentaire(commentaire);
        avis.setDate(LocalDateTime.now());

        return avisRepository.save(avis);
    }
}