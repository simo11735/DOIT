import java.util.ArrayList;
import java.util.List;

public class Progettista {
    private int id;
    private String nome;
    private String cognome;
    private Competenza competenza;
    private List<String> esperienzeLavorative;
    private String linkedinUrl;
    private List<Progetto> progetti = new ArrayList<>();

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
}
