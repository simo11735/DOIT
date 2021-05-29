package com.unicam.ids.doit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class MessaggioProgettista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String testo;
    private boolean giudizio;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"proponenteProgetto", "progettisti", "candidature"})
    private Progetto progetto;
    private boolean giudicato;

    public MessaggioProgettista() {
    }

    public MessaggioProgettista(Progetto progetto) {
        this.testo = "";
        this.giudizio = false;
        this.progetto = progetto;
        this.giudicato = false;
    }

    public int getId() {
        return id;
    }

    public String getTesto() {
        return testo;
    }

    public boolean isGiudizio() {
        return giudizio;
    }

    public Progetto getProgetto() {
        return progetto;
    }

    public boolean isGiudicato() {
        return giudicato;
    }

    public void giudica(String testo, boolean giudizio) {
        this.testo = testo;
        this.giudizio = giudizio;
        this.giudicato = true;
    }
}
