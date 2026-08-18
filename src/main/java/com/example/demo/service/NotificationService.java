package com.example.demo.service;

import com.example.demo.enums.TypeNotificationEnum;
import com.example.demo.model.Client;
import com.example.demo.model.Commande;
import com.example.demo.model.Notification;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ClientService clientService;

    public List<Notification> listerPourUtilisateur(AuthenticatedUser user) {
        if (user.estAdmin()) {
            return notificationRepository.findAll();
        }
        return notificationRepository.findByClientId(user.getUserId());
    }

    public Notification trouverParId(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification introuvable avec l'id : " + id));
    }

    public Notification trouverParIdEtVerifierProprietaire(Long id, AuthenticatedUser user) {
        Notification notification = trouverParId(id);

        if (!user.estAdmin() && !notification.getClient().getId().equals(user.getUserId())) {
            throw new RuntimeException("Accès refusé : cette notification ne vous appartient pas.");
        }

        return notification;
    }

    public Notification envoyer(Long clientId, String message, TypeNotificationEnum type, Commande commande) {
        Client client = clientService.trouverParId(clientId);

        Notification notification = new Notification();
        notification.setClient(client);
        notification.setMessage(message);
        notification.setType(type);
        notification.setCommande(commande);
        notification.setDateEnvoi(LocalDateTime.now());
        notification.setLue(false);

        return notificationRepository.save(notification);
    }

    public Notification marquerLue(Long notificationId, AuthenticatedUser user) {
        Notification notification = trouverParIdEtVerifierProprietaire(notificationId, user);
        notification.setLue(true);
        return notificationRepository.save(notification);
    }
}