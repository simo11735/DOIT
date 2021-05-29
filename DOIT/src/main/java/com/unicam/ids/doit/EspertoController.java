package com.unicam.ids.doit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<MessaggioProponenteProgetto> getMessaggiProponenteProgetto(@CookieValue int id) {
        try {
            Esperto esperto = espertoRepository.findById(id).get();
            return esperto.getMessaggiProponenteProgetto().stream().filter(m -> !m.isGiudicato()).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/giudicaProgettista")
    public ResponseEntity giudicaProgettista(@CookieValue int id, @RequestParam int idMessaggio, @RequestParam String testo, @RequestParam boolean giudizio) {
        try {
            Esperto esperto = espertoRepository.findById(id).get();
            MessaggioProponenteProgetto messaggioProponenteProgetto = messaggioProponenteProgettoRepository.findById(idMessaggio).get();
            if(!esperto.giudicaProgettista(messaggioProponenteProgetto, testo, giudizio))
                throw new Exception();
            espertoRepository.save(esperto);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/messaggiProgettista")
    public List<MessaggioProgettista> getMessaggiProgettista(@CookieValue int id) {
        try {
            Esperto esperto = espertoRepository.findById(id).get();
            return esperto.getMessaggiProgettista().stream().filter(m -> !m.isGiudicato()).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/giudicaProgetto")
    public ResponseEntity giudicaProgetto(@CookieValue int id, @RequestParam int idMessaggio, @RequestParam String testo, @RequestParam boolean giudizio) {
        try {
            Esperto esperto = espertoRepository.findById(id).get();
            MessaggioProgettista messaggioProgettista = messaggioProgettistaRepository.findById(idMessaggio).get();
            if(!esperto.giudicaProgetto(messaggioProgettista, testo, giudizio))
                throw  new Exception();
            espertoRepository.save(esperto);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
