export default Vue.component("proposta-progetto", {
  props: {
    utente: {
      type: Object,
    },
  },
  /*html*/
  template: `<ion-content>
    <tool-bar titolo="proposta progetto"></tool-bar>
    <form @submit.prevent="proponi">
        <ion-item>
            <ion-label>nome</ion-label>
            <ion-input @ionChange="nome = $event.target.value" placeholder="nome"></ion-input>
        </ion-item>
        <ion-item>
            <ion-label>descrizione</ion-label>
            <ion-textarea @ionChange="descrizione = $event.target.value" placeholder="descrizione"></ion-textarea>
        </ion-item>
        <ion-item>
            <ion-label>competenza</ion-label>
            <ion-select @ionChange="competenza = $event.target.value" placeholder="competenza">
            <ion-select-option v-for="competenza in competenze" :value="competenza">{{competenza}}</ion-select-option>
            </ion-select>
        </ion-item>
        <ion-button color="dark" expand="full" type="submit">proponi</ion-button>
    </form>
    </ion-content>
    `,
  data() {
    return {
      nome: "",
      descrizione: "",
      competenza: "",
      competenze: [],
    };
  },
  async created() {
    this.competenze = await (await fetch("competenze")).json();
  },
  methods: {
    async proponi() {
      if (this.nome === "" || this.cognome === "" || this.competenza === "") {
        this.$emit("notifica", "campo mancante");
      } else {
        this.$emit("caricamento", true);
        const status = (
          await fetch(
            "proponenteProgetto/propostaProgetto?nome=" +
              this.nome +
              "&descrizione=" +
              this.descrizione +
              "&competenza=" +
              this.competenza,
            { method: "POST" }
          )
        ).status;
        if (status >= 200 && status < 300) this.$emit("notifica", "successo");
        else this.$emit("notifica", "errore proposta progetto");
        this.$emit("caricamento", false);
      }
    },
  },
});
