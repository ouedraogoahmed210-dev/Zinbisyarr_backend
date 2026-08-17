package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Client creerCompte(Client client, String motDePasseEnClair) {
        clientRepository.findByTelephone(client.getTelephone()).ifPresent(c -> {
            throw new RuntimeException("Un compte existe déjà avec ce numéro de téléphone.");
        });

        client.setMotDePasseHash(passwordEncoder.encode(motDePasseEnClair));
        client.setDateCreation(LocalDateTime.now());
        client.setPointsFidelite(0);

        return clientRepository.save(client);
    }

    public Client seConnecter(String telephone, String motDePasseEnClair) {
        Client client = clientRepository.findByTelephone(telephone)
                .orElseThrow(() -> new RuntimeException("Numéro de téléphone ou mot de passe incorrect."));

        if (!passwordEncoder.matches(motDePasseEnClair, client.getMotDePasseHash())) {
            throw new RuntimeException("Numéro de téléphone ou mot de passe incorrect.");
        }

        return client;
    }
    

    public Client trouverParId(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'id : " + id));
    }

    public Client modifierProfil(Long id, Client donneesModifiees) {
        Client client = trouverParId(id);

        client.setNom(donneesModifiees.getNom());
        client.setLangue(donneesModifiees.getLangue());
        client.setAdresseDefaut(donneesModifiees.getAdresseDefaut());
        client.setQuartier(donneesModifiees.getQuartier());

        return clientRepository.save(client);
    }
    public List<Client> listerTous() {
        return clientRepository.findAll();
}
}