package com.unicam.ids.doit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Progettista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String cognome;
    private Competenza competenza;
    private List<String> esperienzeLavorative;
    private String linkedinUrl;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Progetto> progetti = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<MessaggioProgettista> messaggiProgettista = new ArrayList<>();

    public Progettista(int id, String nome, String cognome, Competenza competenza) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.competenza = competenza;
    }

    public boolean creaCandidatura(Progetto progetto) {
        return progetto.addCandidatura(this);
    }

    boolean addEsperienzaLavorativa(String esperienzaLavorativa) {
        if(!this.esperienzeLavorative.contains(esperienzaLavorativa))
            return this.esperienzeLavorative.add(esperienzaLavorativa);
        return false;
    }

    void richiestaConsiglioProgetto(int id, Progetto progetto, Esperto esperto) {
        MessaggioProgettista mp = new MessaggioProgettista(id, progetto);
        esperto.getMessaggiProgettista().add(mp);
        this.messaggiProgettista.add(mp);
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

    public List<String> getEsperienzeLavorative() {
        return esperienzeLavorative;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public List<Progetto> getProgetti() {
        return progetti;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public List<MessaggioProgettista> getMessaggiProgettista() {
        return messaggiProgettista;
    }
}
