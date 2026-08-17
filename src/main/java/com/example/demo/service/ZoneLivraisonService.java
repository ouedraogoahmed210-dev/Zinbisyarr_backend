package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ZoneLivraison;
import com.example.demo.repository.ZoneLivraisonRepository;

@Service
public class ZoneLivraisonService {

    @Autowired
    private ZoneLivraisonRepository zoneLivraisonRepository;

    private static final BigDecimal TARIF_PAR_DEFAUT = BigDecimal.valueOf(1500);

    public List<ZoneLivraison> listerToutes() {
        return zoneLivraisonRepository.findAll();
    }

    public ZoneLivraison creerOuModifier(ZoneLivraison zone) {
        return zoneLivraisonRepository.save(zone);
    }

    public void supprimer(Long id) {
        zoneLivraisonRepository.deleteById(id);
    }

    public BigDecimal calculerTarif(String quartier) {
        if (quartier == null || quartier.isBlank()) {
            return TARIF_PAR_DEFAUT;
        }
        return zoneLivraisonRepository.findByNomQuartierIgnoreCase(quartier)
                .map(ZoneLivraison::getTarif)
                .orElse(TARIF_PAR_DEFAUT);
    }
}