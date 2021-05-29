package com.unicam.ids.doit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Progetto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String descrizione;
    @Enumerated(EnumType.STRING)
    private Competenza competenza;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("progetti")
    private ProponenteProgetto proponenteProgetto;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("progetti")
    private List<Progettista> candidature = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("progetti")
    private List<Progettista> progettisti = new ArrayList<>();

    public Progetto() {
    }

    public Progetto(String nome, String descrizione, Competenza competenza, ProponenteProgetto proponenteProgetto) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.competenza = competenza;
        this.proponenteProgetto = proponenteProgetto;
    }

    public boolean addCandidatura(Progettista progettista) {
        if (!progettista.getCompetenza().equals(this.competenza) || this.candidature.contains(progettista) ||
                this.progettisti.contains(progettista))
            return false;
        return this.candidature.add(progettista);
    }

    public boolean addProgettista(Progettista progettista) {
        for (Progettista trovato : this.candidature) {
            if (trovato.equals(progettista)) {
                this.candidature.remove(progettista);
                this.progettisti.add(progettista);
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

    public String getDescrizione() {
        return descrizione;
    }

    public Competenza getCompetenza() {
        return competenza;
    }

    public ProponenteProgetto getProponenteProgetto() {
        return proponenteProgetto;
    }

    public List<Progettista> getCandidature() {
        return candidature;
    }

    public List<Progettista> getProgettisti() {
        return progettisti;
    }
}
