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

import com.example.demo.enums.TypeNotificationEnum;
import com.example.demo.model.Notification;
import com.example.demo.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/client/{clientId}")
    public List<Notification> listerParClient(@PathVariable Long clientId) {
        return notificationService.listerParClient(clientId);
    }

    @PostMapping
    public Notification envoyer(@RequestParam Long clientId,
                                 @RequestParam String message,
                                 @RequestParam TypeNotificationEnum type) {
        return notificationService.envoyer(clientId, message, type, null);
    }

    @PutMapping("/{id}/lue")
    public Notification marquerLue(@PathVariable Long id) {
        return notificationService.marquerLue(id);
    }
}