public class MessaggioProgettista {
    private int id;
    private String testo;
    private boolean giudizio;

    public MessaggioProgettista(int id, String testo, boolean giudizio) {
        this.id = id;
        this.testo = testo;
        this.giudizio = giudizio;
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
}
