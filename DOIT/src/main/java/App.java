public class App {
    public static void main(String[] args) {
        ProponenteProgetto proponenteProgetto = new ProponenteProgetto(0, "Proponente", "Progetto");
        Progettista progettista = new Progettista(1, "Progettista", "Progettista", Competenza.INFORMATICA);

        Progetto progetto = proponenteProgetto.propostaProgetto(2, "Progetto", "progetto", Competenza.INFORMATICA);
        progettista.creaCandidatura(progetto);

        System.out .println(progetto.getCandidature());
        System.out .println(progetto.getProgettisti());

        proponenteProgetto.accettaCandidatura(progetto, progettista);

        System.out .println(progetto.getCandidature());
        System.out .println(progetto.getProgettisti());

    }
}
