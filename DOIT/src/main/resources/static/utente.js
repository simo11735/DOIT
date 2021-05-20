export default Vue.component("utente", {
  props: {
    utente: {
      type: Object
    },
  },
  template:
    /*html*/
    `<ion-content>
        <form>
            <ion-list>
                <ion-item>
                    <ion-label>nome</ion-label>
                    <ion-title>{{utente.nome}}</ion-title>
                </ion-item>
                <ion-item>
                    <ion-label>cognome</ion-label>
                    <ion-title>{{utente.cognome}}</ion-title>
                </ion-item>
                <ion-item>
                    <ion-label>tipo</ion-label>
                    <ion-title>{{utente.tipo}}</ion-title>
                </ion-item>
                <ion-item v-if="utente.competenza">
                    <ion-label>competenza</ion-label>
                    <ion-title>{{utente.competenza}}</ion-title>
                </ion-item>
            </ion-list>
            <ion-button expand='full' color="dark" @click="esci">esci</ion-button>
            </form>
        </ion-content>
        `,
  methods: {
    esci() {
      this.$emit("esci");
    },
  },
});