package com.unicam.ids.doit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class MessaggioProponenteProgetto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String testo;
    private boolean giudizio;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"messaggiProgettista", "progetti"})
    private Progettista progettista;
    private boolean giudicato;

    public MessaggioProponenteProgetto() {
    }

    public MessaggioProponenteProgetto(Progettista progettista) {
        this.testo = "";
        this.giudizio = false;
        this.progettista = progettista;
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

    public Progettista getProgettista() {
        return progettista;
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
