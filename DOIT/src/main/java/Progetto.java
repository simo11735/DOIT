import java.util.ArrayList;
import java.util.List;

public class Progetto {
    private int id;
    private String nome;
    private String descrizione;
    private Competenza competenza;
    private ProponenteProgetto proponenteProgetto;
    private List<Progettista> candidature = new ArrayList<>();
    private List<Progettista> progettisti = new ArrayList<>();

    public Progetto(int id, String nome, String descrizione, Competenza competenza, ProponenteProgetto proponenteProgetto) {
        this.id = id;
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
