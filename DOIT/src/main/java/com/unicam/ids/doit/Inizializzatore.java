package com.unicam.ids.doit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Inizializzatore implements CommandLineRunner {
    @Autowired
    ProponenteProgettoRepository proponenteProgettoRepository;
    @Autowired
    ProgettistaRepository progettistaRepository;
    @Autowired
    EspertoRepository espertoRepository;

    @Override
    public void run(String... args) throws Exception {
        ProponenteProgetto p1 = new ProponenteProgetto("proponente", "progetto");
        proponenteProgettoRepository.save(p1);
        System.out.println("Proponente Progetto: " + p1.getId());
        Progettista p2 = new Progettista("progettista", "cognome", Competenza.INFORMATICA);
        progettistaRepository.save(p2);
        System.out.println("Progettista: " + p2.getId());
        Esperto p3 = new Esperto("esperto", "cognome", Competenza.INFORMATICA);
        espertoRepository.save(p3);
        System.out.println("Esperto: " + p3.getId());
    }
}
