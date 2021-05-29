package com.unicam.ids.doit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UtenteController {
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

    @GetMapping("/")
    public RedirectView home() {
        return new RedirectView("/index.html");
    }

    @PostMapping("/registraProponenteProgetto")
    ResponseEntity registraProponenteProgetto(@RequestParam String nome, @RequestParam String cognome, @RequestParam String username, @RequestParam String password) {
        try {
            for (ProponenteProgetto p : proponenteProgettoRepository.findAll())
                if (p.getUsername().equals(username))
                    throw new Exception();
            ProponenteProgetto pp = new ProponenteProgetto(nome, cognome, username, password);
            proponenteProgettoRepository.save(pp);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/registraProgettista")
    ResponseEntity registraProgettista(@RequestParam String nome, @RequestParam String cognome, @RequestParam String username, @RequestParam String password, @RequestParam String competenza) {
        try {
            for (Progettista p : progettistaRepository.findAll())
                if (p.getUsername().equals(username))
                    throw new Exception();
            Progettista p = new Progettista(nome, cognome, username, password, findCompetenza(competenza));
            progettistaRepository.save(p);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/registraEsperto")
    ResponseEntity registraEsperto(@RequestParam String nome, @RequestParam String cognome, @RequestParam String username, @RequestParam String password, @RequestParam String competenza) {
        try {
            for (Esperto e : espertoRepository.findAll())
                if (e.getUsername().equals(username))
                    throw new Exception();
            Esperto e = new Esperto(nome, cognome, username, password, findCompetenza(competenza));
            espertoRepository.save(e);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/autenticaProponenteProgetto")
    ProponenteProgetto autenticaProponenteProgetto(@RequestParam String username, @RequestParam String password) {
        try {
            for (ProponenteProgetto p : proponenteProgettoRepository.findAll())
                if (p.getUsername().equals(username))
                    if (p.controllaPassword(password))
                        return p;
                    else
                        throw new Exception();
            throw new Exception();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/autenticaProgettista")
    Progettista autenticaProgettista(@RequestParam String username, @RequestParam String password) {
        try {
            for (Progettista p : progettistaRepository.findAll())
                if (p.getUsername().equals(username))
                    if (p.controllaPassword(password))
                        return p;
                    else
                        throw new Exception();
            throw new Exception();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/autenticaEsperto")
    Esperto autenticaEsperto(@RequestParam String username, @RequestParam String password) {
        try {
            for (Esperto e : espertoRepository.findAll())
                if (e.getUsername().equals(username))
                    if (e.controllaPassword(password))
                        return e;
                    else
                        throw new Exception();
            throw new Exception();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/progetti")
    List<Progetto> getProgetti(@RequestParam String nome) {
        try {
            List<Progetto> progetti = new ArrayList<>();
            for (Progetto p : progettoRepository.findAll())
                if (p.getNome().contains(nome) || p.getDescrizione().contains(nome))
                    progetti.add(p);
            return progetti;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/progetto")
    Progetto getProgetto(@RequestParam int id) {
        try {
            return progettoRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/proponente-progetti")
    List<ProponenteProgetto> getProponenteProgetti(@RequestParam String nome) {
        try {
            List<ProponenteProgetto> proponenteProgetti = new ArrayList<>();
            for (ProponenteProgetto p : proponenteProgettoRepository.findAll())
                if (p.getNome().contains(nome) || p.getCognome().contains(nome))
                    proponenteProgetti.add(p);
            return proponenteProgetti;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/proponente-progetto")
    ProponenteProgetto getProponenteProgetto(@RequestParam int id) {
        try {
            return proponenteProgettoRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/progettisti")
    List<Progettista> getProgettisti(@RequestParam String nome) {
        try {
            List<Progettista> progettisti = new ArrayList<>();
            for (Progettista p : progettistaRepository.findAll())
                if (p.getNome().contains(nome) || p.getCognome().contains(nome))
                    progettisti.add(p);
            return progettisti;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/progettista")
    Progettista getProgettista(@RequestParam int id) {
        try {
            return progettistaRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/esperti")
    List<Esperto> getEsperti(@RequestParam String nome) {
        try {
            List<Esperto> esperti = new ArrayList<>();
            for (Esperto p : espertoRepository.findAll())
                if (p.getNome().contains(nome) || p.getCognome().contains(nome))
                    esperti.add(p);
            return esperti;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/esperto")
    Esperto getEsperto(@RequestParam int id) {
        try {
            return espertoRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/competenze")
    List<Competenza> getCompetenze() {
        try {
            return List.of(Competenza.values());
        } catch (Exception e) {
            return null;
        }
    }
}
