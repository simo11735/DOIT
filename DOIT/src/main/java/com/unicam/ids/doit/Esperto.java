package com.unicam.ids.doit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Esperto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String cognome;
    private Competenza competenza;
    @OneToMany(cascade = CascadeType.ALL)
    private List<MessaggioProgettista> messaggiProgettista = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<MessaggioProponenteProgetto> messaggiProponenteProgetto = new ArrayList<>();

    public Esperto(int id, String nome, String cognome, Competenza competenza) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.competenza = competenza;
    }

    public void giudicaProgettista(MessaggioProgettista messaggioProgettista, String testo, boolean giudizio) {
        messaggioProgettista.giudica(testo, giudizio);
    }

    public void giudicaProponenteProgetto(MessaggioProponenteProgetto messaggioProgettista, String testo, boolean giudizio) {
        messaggioProgettista.giudica(testo, giudizio);
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

    public Competenza getCompetenza() {
        return competenza;
    }

    public List<MessaggioProgettista> getMessaggiProgettista() {
        return messaggiProgettista;
    }

    public List<MessaggioProponenteProgetto> getMessaggiProponenteProgetto() {
        return messaggiProponenteProgetto;
    }
}
