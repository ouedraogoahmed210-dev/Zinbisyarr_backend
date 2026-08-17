package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.TypeNotificationEnum;
import com.example.demo.model.Administrateur;
import com.example.demo.model.CampagneMarketing;
import com.example.demo.model.Client;
import com.example.demo.repository.CampagneMarketingRepository;

@Service
public class CampagneMarketingService {

    @Autowired
    private CampagneMarketingRepository campagneMarketingRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private NotificationService notificationService;

    public List<CampagneMarketing> listerToutes() {
        return campagneMarketingRepository.findAll();
    }

    public CampagneMarketing trouverParId(Long id) {
        return campagneMarketingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campagne introuvable avec l'id : " + id));
    }

    public CampagneMarketing creerCampagne(Administrateur admin, String titre, String message,
                                            String segmentCible, List<Long> clientIds) {
        CampagneMarketing campagne = new CampagneMarketing();
        campagne.setAdministrateur(admin);
        campagne.setTitre(titre);
        campagne.setMessage(message);
        campagne.setSegmentCible(segmentCible);

        List<Client> clients = clientIds.stream()
                .map(clientService::trouverParId)
                .toList();
        campagne.setClientsCibles(clients);

        return campagneMarketingRepository.save(campagne);
    }

    public CampagneMarketing envoyerMessageGroupe(Long campagneId) {
        CampagneMarketing campagne = trouverParId(campagneId);

        for (Client client : campagne.getClientsCibles()) {
            notificationService.envoyer(
                    client.getId(),
                    campagne.getMessage(),
                    TypeNotificationEnum.PROMOTION,
                    null
            );
        }

        campagne.setDateEnvoi(LocalDateTime.now());
        return campagneMarketingRepository.save(campagne);
    }
}