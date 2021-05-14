package com.unicam.ids.doit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/proponenteProgetto")
public class ProponenteProgettoController {
    @Autowired
    ProponenteProgettoRepository proponenteProgettoRepository;
    @Autowired
    ProgettoRepository progettoRepository;
    @Autowired
    ProgettistaRepository progettistaRepository;
    @Autowired
    EspertoRepository espertoRepository;

    private Competenza findCompetenza(String word) throws Exception {
        for (Competenza c : Competenza.values())
            if (c.toString().equalsIgnoreCase(word))
                return c;
        throw new Exception();
    }

    @PostMapping("/propostaProgetto")
    ResponseEntity propostaProgetto(@CookieValue int idPP, @RequestParam String nome, @RequestParam String descrizione, @RequestParam String competenza) {
        try {
            ProponenteProgetto pp = proponenteProgettoRepository.findById(idPP).get();
            Progetto p = pp.propostaProgetto(nome, descrizione, findCompetenza(competenza));
            progettoRepository.save(p);
            proponenteProgettoRepository.save(pp);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/progetti")
    List<Progetto> getProgetti(@CookieValue int idPP) {
        try {
            ProponenteProgetto pp = proponenteProgettoRepository.findById(idPP).get();
            return pp.getProgetti();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/candidature")
    List<Progettista> getCandidature(@CookieValue int idPP, @RequestParam int idProgetto) {
        try {
            ProponenteProgetto pp = proponenteProgettoRepository.findById(idPP).get();
            Progetto p = progettoRepository.findById(idProgetto).get();
            if (!pp.getProgetti().contains(p))
                throw new Exception();
            return p.getCandidature();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/accettaCandidatura")
    ResponseEntity accettaCandidatura(@CookieValue int idPP, @RequestParam int idProgetto, @RequestParam int idProgettista) {
        try {
            ProponenteProgetto pp = proponenteProgettoRepository.findById(idPP).get();
            Progetto progetto = progettoRepository.findById(idProgetto).get();
            Progettista progettista = progettistaRepository.findById(idProgettista).get();
            if (!pp.accettaCandidatura(progetto, progettista))
                throw new Exception();
            proponenteProgettoRepository.save(pp);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/progettisti")
    List<Progettista> getProgettisti(@CookieValue int idPP, @RequestParam int idProgetto) {
        try {
            ProponenteProgetto pp = proponenteProgettoRepository.findById(idPP).get();
            Progetto progetto = progettoRepository.findById(idProgetto).get();
            List<Progettista> progettisti = new ArrayList<>();
            progettistaRepository.findAll().forEach(p -> {
                if (pp.getProgetti().contains(progetto))
                    if (progetto.getCompetenza().equals(p.getCompetenza()))
                        progettisti.add(p);
            });
            return progettisti;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/esperti")
    List<Esperto> getEsperti(@CookieValue int idPP, @RequestParam int idProgetto) {
        try {
            ProponenteProgetto pp = proponenteProgettoRepository.findById(idPP).get();
            Progetto progetto = progettoRepository.findById(idProgetto).get();
            List<Esperto> esperti = new ArrayList<>();
            espertoRepository.findAll().forEach(e -> {
                if (pp.getProgetti().contains(progetto))
                    if (progetto.getCompetenza().equals(e.getCompetenza()))
                        esperti.add(e);
            });
            return esperti;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/richiestaConsiglioProgettista")
    ResponseEntity richiestaConsiglioProgettista(@CookieValue int idPP, @RequestParam int idProgettista, @RequestParam int idEsperto) {
        try {
            ProponenteProgetto pp = proponenteProgettoRepository.findById(idPP).get();
            Progettista progettista = progettistaRepository.findById(idProgettista).get();
            Esperto esperto = espertoRepository.findById(idEsperto).get();
            if (!pp.richiestaConsiglioProgettista(progettista, esperto))
                throw new Exception();
            proponenteProgettoRepository.save(pp);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
