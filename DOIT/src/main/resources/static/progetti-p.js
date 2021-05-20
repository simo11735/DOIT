export default Vue.component("progetti-pp", {
  props: {
    utente: {
      type: Object,
    },
  },
  /*html*/
  template: `
  <ion-content>
  <tool-bar titolo="progetti progettista"></tool-bar>
            <ion-card v-for="progetto in progetti">
            <ion-card-header>
                <ion-card-title>{{progetto.nome}}</ion-card-title>
                    <ion-card-subtitle>{{progetto.competenza}}</ion-card-subtitle>
                    </ion-card-header>
                <ion-card-content>
                    <ion-card-description>{{progetto.descrizione}}</ion-card-description>
                </ion-card-content>
            </ion-card>
        </ion-content>
        `,
  data() {
    return {
      progetti: [],
    };
  },
  async created() {
    this.$emit("caricamento", true);
    this.progetti = await (await fetch("progettista/progetti")).json();
    this.$emit("caricamento", false);
  },
});
