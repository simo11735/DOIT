package com.unicam.ids.doit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerService {
    @Autowired
    ProponenteProgettoRepository proponenteProgettoRepository;
    @Autowired
    ProgettoRepository progettoRepository;
    @Autowired
    ProgettistaRepository progettistaRepository;
    @Autowired
    EspertoRepository espertoRepository;

    @PostMapping("/propostaProgetto")
    void propostaProgetto(@RequestParam int idPP, @RequestParam String nome, @RequestParam String descrizione, @RequestParam Competenza competenza) {
        ProponenteProgetto pp = proponenteProgettoRepository.findById(idPP).get();
        Progetto p = pp.propostaProgetto(nome, descrizione, competenza);
        progettoRepository.save(p);
        proponenteProgettoRepository.save(pp);
    }

    @PostMapping("/accettaCandidatura")
    void accettaCandidatura(@RequestParam int idPP, @RequestParam int idProgetto, @RequestParam int idProgettista) {
        ProponenteProgetto pp = proponenteProgettoRepository.findById(idPP).get();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        Progettista progettista = progettistaRepository.findById(idProgettista).get();
        pp.accettaCandidatura(progetto, progettista);
        proponenteProgettoRepository.save(pp);
    }

    @PostMapping("/richiestaConsiglioProgettista")
    void richiestaConsiglioProgettista(@RequestParam int idPP, @RequestParam int idProgettista, @RequestParam int idEsperto) {
        ProponenteProgetto pp = proponenteProgettoRepository.findById(idPP).get();
        Progettista progettista = progettistaRepository.findById(idProgettista).get();
        Esperto esperto = espertoRepository.findById(idProgettista).get();
        pp.richiestaConsiglioProgettista(progettista, esperto);
        proponenteProgettoRepository.save(pp);
    }
}
