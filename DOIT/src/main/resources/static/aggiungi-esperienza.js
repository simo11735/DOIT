export default Vue.component("aggiungi-esperienza", {
  props: {
    utente: {
      type: Object,
    },
  },
  template: /*html*/ `
        <ion-content>
            <tool-bar titolo="esperienze"></tool-bar>
            <form @submit.prevent="aggiungiEsperienza">
                <ion-item>
                    <ion-label>esperienza</ion-label>
                    <ion-textarea @ionChange="testo = $event.target.value" placeholder="testo"></ion-textarea>
                </ion-item>
                <ion-button color="dark" expand="full" type="submit">aggiungi</ion-button>
            </form>
            <form @submit.prevent="setLinkedin">
                <ion-item>
                    <ion-label>linkedin</ion-label>
                    <ion-input @ionChange="linkedin = $event.target.value" placeholder="testo"></ion-input>
                </ion-item>
                <ion-button color="dark" expand="full" type="submit">set</ion-button>
            </form>
            <ion-list>
                <ion-item v-if="utente.linkedinUrl">
                    <ion-title>url linkedin</ion-title>
                        <ion-label>
                            <a :href="linkedin">{{utente.linkedinUrl}}
                        </a>
                    </ion-label>
                </ion-item>
                <ion-item>
                    <ion-title>esperienze lavorative</ion-title>
                </ion-item>
                <ion-item v-for="esperienza in utente.esperienzeLavorative">
                    <ion-label>{{esperienza}}</ion-label>
                </ion-item>
            </ion-list>
        </ion-content>
        `,
  data() {
    return {
      esperienze: [],
      testo: "",
      linkedin: this.utente.linkedinUrl ? this.utente.linkedinUrl : '',
    };
  },
  async created() {
    this.aggiorna();
  },
  methods: {
    async aggiorna() {
      this.$emit("caricamento", true);
      await this.$emit("aggiorna");
      this.esperienze = this.utente.esperienzeLavorative;
      this.$emit("caricamento", false);
    },
    async aggiungiEsperienza() {
      if (this.testo === "") {
        this.$emit("notifica", "campo mancante");
      } else {
        this.$emit("caricamento", true);
        const status = (
          await fetch(
            "/progettista/addEsperienzaLavorativa?esperienzaLavorativa=" +
              this.testo,
            { method: "POST" }
          )
        ).status;
        if (status >= 200 && status < 300) this.$emit("notifica", "successo");
        else this.$emit("notifica", "errore aggiunta");
        await this.aggiorna();
        this.$emit("caricamento", false);
      }
    },
    async setLinkedin() {
      if (this.linkedin === "") {
        this.$emit("notifica", "campo mancante");
      } else {
        this.$emit("caricamento", true);
        const status = (
          await fetch("/progettista/setLinkedin?linkedin=" + this.linkedin, {
            method: "POST",
          })
        ).status;
        if (status >= 200 && status < 300) this.$emit("notifica", "successo");
        else this.$emit("notifica", "errore linkedin");
        await this.aggiorna();
        this.$emit("caricamento", false);
      }
    },
  },
});
