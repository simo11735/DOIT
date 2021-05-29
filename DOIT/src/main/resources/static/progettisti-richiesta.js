export default Vue.component("progettisti-richiesta", {
  props: {
    utente: {
      type: Object,
    },
  },
  /*html*/
  template: `
    <ion-content>
    <tool-bar titolo="progettisti"></tool-bar>
      <ion-card v-for="progettista in progettisti" button @click="$router.push({path: '/esperti-richiesta/'+$route.params.idProgetto+'/'+progettista.id})">
        <ion-card-header>
          <ion-card-title>{{progettista.nome}} {{progettista.cognome}}</ion-card-title>
          <ion-card-subtitle>{{progettista.competenza}}</ion-card-subtitle>
        </ion-card-header>
      </ion-card>
    </ion-content>
          `,
  data() {
    return {
      progettisti: [],
    };
  },
  async created() {
    this.$emit("caricamento", true);
    this.progettisti = await (
      await fetch(
        "/proponenteProgetto/progettisti?idProgetto=" +
          this.$route.params.idProgetto
      )
    ).json();
    this.$emit("caricamento", false);
  },
});
