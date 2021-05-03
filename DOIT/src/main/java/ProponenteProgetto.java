import java.util.ArrayList;
import java.util.List;

public class ProponenteProgetto {
    private int id;
    private String nome;
    private String cognome;
    private List<Progetto> progetti = new ArrayList<>();
    private List<MessaggioProponenteProgetto> messaggiProponenteProgetto = new ArrayList<>();

    public ProponenteProgetto(int id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    public Progetto propostaProgetto(int id, String nome, String descrizione, Competenza competenza) {
        Progetto progetto = new Progetto(id, nome, descrizione, competenza, this);
        this.progetti.add(progetto);
        return progetto;
    }

    public boolean accettaCandidatura(Progetto progetto, Progettista progettista) {
        return progetto.addProgettista(progettista);
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
