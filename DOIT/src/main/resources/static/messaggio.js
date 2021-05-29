export default Vue.component("messaggio", {
  props: {
    utente: {
      type: Object,
    },
  },
  template: /*html*/ `
      <ion-content>
          <tool-bar titolo="messaggio"></tool-bar>
          <form @submit.prevent="invia">
              <ion-item>
                  <ion-label>testo</ion-label>
                  <ion-textarea @ionChange="testo = $event.target.value" placeholder="testo"></ion-textarea>
              </ion-item>
              <ion-item>
                <ion-label>approva<ion-label>
                <ion-toggle @ionChange="giudizio = !giudizio"></ion-toggle>
              </ion-item>
              <ion-button color="dark" expand="full" type="submit">invia</ion-button>
          </form>
      </ion-content>
      `,
  data() {
    return {
      messaggio: undefined,
      testo: "",
      giudizio: false,
    };
  },
  methods: {
    async invia() {
      if (this.testo === "") {
        this.$emit("notifica", "campo mancante");
      } else {
        this.$emit("caricamento", true);
        const status = (
          await fetch(
            "/" +
              this.utente.tipo +
              "/giudica" +
              this.$route.params.tipo +
              "?idMessaggio=" +
              this.$route.params.id +
              "&testo=" +
              this.testo +
              "&giudizio=" +
              this.giudizio,
            { method: "POST" }
          )
        ).status;
        if (status >= 200 && status < 300) this.$emit("notifica", "successo");
        else this.$emit("notifica", "errore consiglio");
        await this.$emit('aggiorna');
        this.$router.go(-1);
        this.$emit("caricamento", false);
      }
    },
  },
});
