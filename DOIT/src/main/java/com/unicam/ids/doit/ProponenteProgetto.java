package com.unicam.ids.doit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProponenteProgetto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String cognome;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Progetto> progetti = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<MessaggioProponenteProgetto> messaggiProponenteProgetto = new ArrayList<>();

    public ProponenteProgetto(int id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    public Progetto propostaProgetto(String nome, String descrizione, Competenza competenza) {
        Progetto progetto = new Progetto(nome, descrizione, competenza, this);
        this.progetti.add(progetto);
        return progetto;
    }

    public boolean accettaCandidatura(Progetto progetto, Progettista progettista) {
        return progetto.addProgettista(progettista);
    }

    void richiestaConsiglioProgettista(Progettista progettista, Esperto esperto) {
        MessaggioProponenteProgetto mp = new MessaggioProponenteProgetto(progettista);
        esperto.getMessaggiProponenteProgetto().add(mp);
        this.messaggiProponenteProgetto.add(mp);
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

    public List<MessaggioProponenteProgetto> getMessaggiProponenteProgetto() {
        return messaggiProponenteProgetto;
    }
}
