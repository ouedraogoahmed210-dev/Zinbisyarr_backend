package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.TypeNotificationEnum;
import com.example.demo.model.Client;
import com.example.demo.model.Commande;
import com.example.demo.model.Notification;
import com.example.demo.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ClientService clientService;

    public List<Notification> listerParClient(Long clientId) {
        return notificationRepository.findByClientId(clientId);
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

    public Notification marquerLue(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification introuvable avec l'id : " + notificationId));
        notification.setLue(true);
        return notificationRepository.save(notification);
    }
}