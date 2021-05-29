export default Vue.component("progetti", {
  props: {
    utente: {
      type: Object,
    },
  },
  /*html*/
  template: `
  <ion-content>
  <tool-bar :titolo="'progetti ' + this.$route.params.tipo"></tool-bar>
    <ion-card v-for="progetto in progetti" button @click="$router.push({path: '/progetto/'+progetto.id})">
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
    this.progetti = await (
      await fetch(this.$route.params.tipo + "/progetti")
    ).json();
    this.$emit("caricamento", false);
  },
});
