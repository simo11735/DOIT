package com.unicam.ids.doit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/esperto")
public class EspertoController {
    @Autowired
    EspertoRepository espertoRepository;
    @Autowired
    MessaggioProgettistaRepository messaggioProgettistaRepository;
    @Autowired
    MessaggioProponenteProgettoRepository messaggioProponenteProgettoRepository;

    @PostMapping("/giudicaProponenteProgetto")
    public ResponseEntity giudicaProponenteProgetto(@CookieValue int idEsperto, @RequestParam int idMessaggioPP, @RequestParam String testo, @RequestParam boolean giudizio) {
        try {
            Esperto esperto = espertoRepository.findById(idEsperto).get();
            MessaggioProponenteProgetto messaggioProponenteProgetto = messaggioProponenteProgettoRepository.findById(idMessaggioPP).get();
            if(!esperto.giudicaProponenteProgetto(messaggioProponenteProgetto, testo, giudizio))
                throw new Exception();
            espertoRepository.save(esperto);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/giudicaProgettista")
    public ResponseEntity giudicaProgettista(@CookieValue int idEsperto, @RequestParam int idMessaggioProgettista, @RequestParam String testo, @RequestParam boolean giudizio) {
        try {
            Esperto esperto = espertoRepository.findById(idEsperto).get();
            MessaggioProgettista messaggioProgettista = messaggioProgettistaRepository.findById(idMessaggioProgettista).get();
            if(!esperto.giudicaProgettista(messaggioProgettista, testo, giudizio))
                throw  new Exception();
            espertoRepository.save(esperto);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
