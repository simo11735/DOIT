package com.unicam.ids.doit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/progettista")
public class ProgettistaController {
    @Autowired
    ProgettistaRepository progettistaRepository;
    @Autowired
    ProgettoRepository progettoRepository;
    @Autowired
    EspertoRepository espertoRepository;

    @PostMapping("/creaCandidatura")
    ResponseEntity creaCandidatura(@CookieValue int idProgettista, @RequestParam int idProgetto) {
        try {
            Progettista progettista = progettistaRepository.findById(idProgettista).get();
            Progetto progetto = progettoRepository.findById(idProgetto).get();
            if(!progettista.creaCandidatura(progetto))
                throw  new Exception();
            progettoRepository.save(progetto);
            progettistaRepository.save(progettista);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/addEsperienzaLavorativa")
    ResponseEntity addEsperienzaLavorativa(@CookieValue int idProgettista, @RequestParam String esperienzaLavorativa) {
        try {
            Progettista progettista = progettistaRepository.findById(idProgettista).get();
            if(!progettista.addEsperienzaLavorativa(esperienzaLavorativa))
                throw new Exception();
            progettistaRepository.save(progettista);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/richiestaConsiglioProgetto")
    ResponseEntity richiestaConsiglioProgetto(@CookieValue int idProgettista, @RequestParam int idProgetto, @RequestParam int idEsperto) {
        try {
            Progettista progettista = progettistaRepository.findById(idProgettista).get();
            Progetto progetto = progettoRepository.findById(idProgetto).get();
            Esperto esperto = espertoRepository.findById(idEsperto).get();
            if(!progettista.richiestaConsiglioProgetto(progetto, esperto))
                throw  new Exception();
            espertoRepository.save(esperto);
            progettistaRepository.save(progettista);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
