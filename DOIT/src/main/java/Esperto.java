import java.util.ArrayList;
import java.util.List;

public class Esperto {

    private int id;
    private String nome;
    private String cognome;
    private Competenza competenza;
    private List<MessaggioProgettista> messaggiProgettista = new ArrayList<>();
    private List<MessaggioProponenteProgetto> messaggiProponenteProgetto = new ArrayList<>();

    public Esperto(int id, String nome, String cognome, Competenza competenza) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.competenza = competenza;
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
