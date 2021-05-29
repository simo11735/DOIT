export default Vue.component("esperti-richiesta", {
  props: {
    utente: {
      type: Object,
    },
  },
  /*html*/
  template: `
    <ion-content>
    <tool-bar titolo="esperti"></tool-bar>
      <ion-card v-for="esperto in esperti" button @click="richiesta(esperto)">
        <ion-card-header>
          <ion-card-title>{{esperto.nome}} {{esperto.cognome}}</ion-card-title>
          <ion-card-subtitle>{{esperto.competenza}}</ion-card-subtitle>
        </ion-card-header>
      </ion-card>
    </ion-content>
          `,
  data() {
    return {
      esperti: [],
    };
  },
  async created() {
    this.$emit("caricamento", true);
    let tipo = "";
    if (this.utente.tipo === "proponente-progetto") tipo = "proponenteProgetto";
    else if (this.utente.tipo === "progettista") tipo = "progettista";
    this.esperti = await (
      await fetch(
        "/" + tipo + "/esperti?idProgetto=" + this.$route.params.idProgetto
      )
    ).json();
    this.$emit("caricamento", false);
  },
  methods: {
    async richiesta(esperto) {
      this.$emit("caricamento", true);
      let status = 0;
      if (this.utente.tipo === "proponente-progetto") {
        status = (
          await fetch(
            "/proponenteProgetto/richiestaConsiglioProgettista?idProgettista=" +
              this.$route.params.idProgettista +
              "&idEsperto=" +
              esperto.id,
            { method: "POST" }
          )
        ).status;
      } else if (this.utente.tipo === "progettista") {
        status = (
          await fetch(
            "/progettista/richiestaConsiglioProgetto?idProgetto=" +
              this.$route.params.idProgetto +
              "&idEsperto=" +
              esperto.id,
            { method: "POST" }
          )
        ).status;
      }
      if (status >= 200 && status < 300) this.$emit("notifica", "successo");
      else this.$emit("notifica", "errore nella richiesta");
      this.$emit("caricamento", false);
    },
  },
});
