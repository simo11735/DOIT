export default Vue.component("messaggi", {
  props: {
    utente: {
      type: Object,
    },
  },
  /*html*/
  template: `
    <ion-content>
    <tool-bar titolo="messaggi"></tool-bar>
      <ion-card v-for="messaggio in messaggi">
        <ion-card-header>
          <ion-card-title>{{messaggio[utente.tipo == 'proponente-progetto' ? 'progettista' : 'progetto'].nome}}</ion-card-title>
          <ion-card-subtitle v-if="messaggio.giudicato">{{messaggio.giudizio ? 'approvato' : 'rifiutato'}}</ion-card-subtitle>
          <ion-card-subtitle v-if="!messaggio.giudicato">in fase di giudizio...</ion-card-subtitle>
        </ion-card-header>
        <ion-card-content v-if="messaggio.giudicato">
          <ion-card-description>{{messaggio.testo}}</ion-card-description>
        </ion-card-content>
      </ion-card>
    </ion-content>
          `,
  data() {
    return {
      messaggi: undefined,
    };
  },
  async created() {
    this.$emit("caricamento", true);
    await this.$emit("aggiorna");
    this.messaggi = this.utente.messaggi;
    this.$emit("caricamento", false);
  },
});
