export default Vue.component("progettista-profilo", {
  props: {
    utente: {
      type: Object,
    },
  },
  template: /*html*/ `
    <ion-content>
        <tool-bar titolo="progettista"></tool-bar>
        <ion-list>
            <ion-item>
                <ion-label>nome</ion-label>    
                <ion-title>{{progettista.nome}}</ion-title>
            </ion-item>
            <ion-item>
                <ion-label>cognome</ion-label>    
                <ion-title>{{progettista.cognome}}</ion-title>
            </ion-item>
            <ion-item>
                <ion-label>competenza</ion-label>    
                <ion-title>{{progettista.competenza}}</ion-title>
            </ion-item>
        </ion-list>
        <ion-list v-if="progettista.progetti.length > 0">
            <ion-title>progetti</ion-title>
            <ion-item v-for="(progetto, index) in progettista.progetti">
                <ion-label>{{progetto.nome}} {{progetto.cognome}}</ion-label>
            </ion-item>
        </ion-list>
    </ion-content>
    `,
  data() {
    return {
      progettista: undefined,
    };
  },
  async created() {
    this.$emit("caricamento", true);
    this.progettista = await (
      await fetch("/progettista?id=" + this.$route.params.id)
    ).json();
    this.$emit("caricamento", false);
  },
});
