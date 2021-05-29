export default Vue.component("invia-candidatura", {
  props: {
    utente: {
      type: Object,
    },
  },
  template: /*html*/ `
    <ion-content>
        <tool-bar titolo="invio candidatura"></tool-bar>
        <ion-list>
            <ion-item v-for="progetto in progetti" detail button @click="$router.push({path: '/progetto/'+progetto.id})">
                <ion-label>{{progetto.nome}}</ion-label>
            </ion-item>
        </ion-list>
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
      await fetch("progettista/progettiCandidatura")
    ).json();
    this.$emit("caricamento", false);
  },
});
