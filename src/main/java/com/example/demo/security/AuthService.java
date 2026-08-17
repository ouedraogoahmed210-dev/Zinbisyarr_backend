package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Administrateur;
import com.example.demo.model.Client;
import com.example.demo.repository.AdministrateurRepository;
import com.example.demo.repository.ClientRepository;

@Service
public class AuthService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse seConnecter(String telephone, String motDePasse) {
        // On essaie d'abord Client
        var clientOpt = clientRepository.findByTelephone(telephone);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            if (passwordEncoder.matches(motDePasse, client.getMotDePasseHash())) {
                String token = jwtUtil.genererToken(telephone, "CLIENT", client.getId());
                return new AuthResponse(token, "CLIENT", client.getId(), client.getNom());
            }
            throw new RuntimeException("Numéro de téléphone ou mot de passe incorrect.");
        }

        // Sinon on essaie Administrateur
        var adminOpt = administrateurRepository.findByTelephone(telephone);
        if (adminOpt.isPresent()) {
            Administrateur admin = adminOpt.get();
            if (passwordEncoder.matches(motDePasse, admin.getMotDePasseHash())) {
                String token = jwtUtil.genererToken(telephone, "ADMIN", admin.getId());
                return new AuthResponse(token, "ADMIN", admin.getId(), admin.getNom());
            }
            throw new RuntimeException("Numéro de téléphone ou mot de passe incorrect.");
        }

        throw new RuntimeException("Numéro de téléphone ou mot de passe incorrect.");
    }
}