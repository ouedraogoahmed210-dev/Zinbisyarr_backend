package com.example.demo.controller;

import com.example.demo.enums.TypeNotificationEnum;
import com.example.demo.model.Notification;
import com.example.demo.security.AuthenticatedUser;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> listerPourUtilisateur(@AuthenticationPrincipal AuthenticatedUser user) {
        return notificationService.listerPourUtilisateur(user);
    }

    @PostMapping
    public Notification envoyer(@RequestParam Long clientId,
                                 @RequestParam String message,
                                 @RequestParam TypeNotificationEnum type) {
        return notificationService.envoyer(clientId, message, type, null);
    }

    @PutMapping("/{id}/lue")
    public Notification marquerLue(@PathVariable Long id, @AuthenticationPrincipal AuthenticatedUser user) {
        return notificationService.marquerLue(id, user);
    }
}