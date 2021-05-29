package com.unicam.ids.doit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ProponenteProgetto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Progetto> progetti = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<MessaggioProponenteProgetto> messaggi = new ArrayList<>();
    private String tipo = "proponente-progetto";

    public ProponenteProgetto() {
    }

    public ProponenteProgetto(String nome, String cognome, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
    }

    public Progetto propostaProgetto(String nome, String descrizione, Competenza competenza) {
        Progetto progetto = new Progetto(nome, descrizione, competenza, this);
        this.progetti.add(progetto);
        return progetto;
    }

    public boolean accettaCandidatura(Progetto progetto, Progettista progettista) {
        if (progetto.getCandidature().stream().map(c -> c.getId()).collect(Collectors.toList()).contains(progettista.getId()))
            if (!progetto.getProgettisti().stream().map(c -> c.getId()).collect(Collectors.toList()).contains(progettista.getId()))
                return progetto.addProgettista(progettista) && progettista.getProgetti().add(progetto);
        return false;
    }

    boolean richiestaConsiglioProgettista(Progettista progettista, Esperto esperto) {
        if (progettista.getCompetenza().equals(esperto.getCompetenza())) {
            if (!esperto.getMessaggiProponenteProgetto().stream().map(p -> p.getProgettista().getId())
                    .collect(Collectors.toList()).contains(progettista.getId())) {
                MessaggioProponenteProgetto mp = new MessaggioProponenteProgetto(progettista);
                esperto.getMessaggiProponenteProgetto().add(mp);
                this.messaggi.add(mp);
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public List<Progetto> getProgetti() {
        return progetti;
    }

    public List<MessaggioProponenteProgetto> getMessaggi() {
        return messaggi;
    }

    public String getTipo() {
        return tipo;
    }

    public String getUsername() {
        return username;
    }

    public boolean controllaPassword(String password) {
        return this.password.equals(password);
    }
}
