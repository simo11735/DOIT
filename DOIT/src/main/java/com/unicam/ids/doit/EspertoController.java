package com.unicam.ids.doit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/esperto")
public class EspertoController {
    @Autowired
    EspertoRepository espertoRepository;
    @Autowired
    MessaggioProgettistaRepository messaggioProgettistaRepository;
    @Autowired
    MessaggioProponenteProgettoRepository messaggioProponenteProgettoRepository;

    @GetMapping("/messaggiProponenteProgetto")
    public List<MessaggioProponenteProgetto> getMessaggiProponenteProgetto(@CookieValue int idEsperto) {
        try {
            Esperto esperto = espertoRepository.findById(idEsperto).get();
            return esperto.getMessaggiProponenteProgetto();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/giudicaProgettista")
    public ResponseEntity giudicaProgettista(@CookieValue int idEsperto, @RequestParam int idMessaggioPP, @RequestParam String testo, @RequestParam boolean giudizio) {
        try {
            Esperto esperto = espertoRepository.findById(idEsperto).get();
            MessaggioProponenteProgetto messaggioProponenteProgetto = messaggioProponenteProgettoRepository.findById(idMessaggioPP).get();
            if(!esperto.giudicaProgettista(messaggioProponenteProgetto, testo, giudizio))
                throw new Exception();
            espertoRepository.save(esperto);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/messaggiProgettista")
    public List<MessaggioProgettista> getMessaggiProgettista(@CookieValue int idEsperto) {
        try {
            Esperto esperto = espertoRepository.findById(idEsperto).get();
            return esperto.getMessaggiProgettista();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/giudicaProgetto")
    public ResponseEntity giudicaProgetto(@CookieValue int idEsperto, @RequestParam int idMessaggioProgettista, @RequestParam String testo, @RequestParam boolean giudizio) {
        try {
            Esperto esperto = espertoRepository.findById(idEsperto).get();
            MessaggioProgettista messaggioProgettista = messaggioProgettistaRepository.findById(idMessaggioProgettista).get();
            if(!esperto.giudicaProgetto(messaggioProgettista, testo, giudizio))
                throw  new Exception();
            espertoRepository.save(esperto);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
