package com.unicam.ids.doit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Progettista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Competenza competenza;
    @ElementCollection
    private List<String> esperienzeLavorative;
    private String linkedinUrl;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Progetto> progetti = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<MessaggioProgettista> messaggi = new ArrayList<>();
    private String tipo = "progettista";

    public Progettista() {
    }

    public Progettista(String nome, String cognome, String username, String password, Competenza competenza) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.competenza = competenza;
    }

    public boolean creaCandidatura(Progetto progetto) {
        if (!progetto.getCandidature().stream().map(c -> c.getId()).collect(Collectors.toList()).contains(this.id))
            if (progetto.getCompetenza().equals(this.competenza))
                return progetto.addCandidatura(this);
        return false;
    }

    public boolean addEsperienzaLavorativa(String esperienzaLavorativa) {
        if (!this.esperienzeLavorative.contains(esperienzaLavorativa))
            return this.esperienzeLavorative.add(esperienzaLavorativa);
        return false;
    }

    public boolean richiestaConsiglioProgetto(Progetto progetto, Esperto esperto) {
        if (progetto.getCompetenza().equals(esperto.getCompetenza())) {
            if (!esperto.getMessaggiProgettista().stream().map(p -> p.getProgetto().getId())
                    .collect(Collectors.toList()).contains(progetto.getId())) {
                MessaggioProgettista mp = new MessaggioProgettista(progetto);
                esperto.getMessaggiProgettista().add(mp);
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

    public List<MessaggioProgettista> getMessaggi() {
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
