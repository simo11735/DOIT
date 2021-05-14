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
    @Enumerated(EnumType.STRING)
    private Competenza competenza;
    @OneToMany(mappedBy = "id")
    private List<MessaggioProgettista> messaggiProgettista = new ArrayList<>();
    @OneToMany(mappedBy = "id")
    private List<MessaggioProponenteProgetto> messaggiProponenteProgetto = new ArrayList<>();

    public Esperto() {
    }

    public Esperto(String nome, String cognome, Competenza competenza) {
        this.nome = nome;
        this.cognome = cognome;
        this.competenza = competenza;
    }

    public boolean giudicaProgettista(MessaggioProgettista messaggioProgettista, String testo, boolean giudizio) {
        if (!messaggioProgettista.isGiudicato()) {
            messaggioProgettista.giudica(testo, giudizio);
            return true;
        }
        return false;
    }

    public boolean giudicaProponenteProgetto(MessaggioProponenteProgetto messaggioPP, String testo, boolean giudizio) {
        if (!messaggioPP.isGiudicato()) {
            messaggioPP.giudica(testo, giudizio);
            return true;
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
